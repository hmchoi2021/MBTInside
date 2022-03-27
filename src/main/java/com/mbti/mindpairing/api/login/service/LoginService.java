package com.mbti.mindpairing.api.login.service;

import com.fasterxml.jackson.core.JsonParser;
import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.exception.MBTIException;
import com.mbti.mindpairing.api.login.dto.KakaoUser;
import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.*;
import com.mbti.mindpairing.api.login.mapstruct.InterestingMapper;
import com.mbti.mindpairing.api.login.mapstruct.MbtiMapper;
import com.mbti.mindpairing.api.login.mapstruct.TermsInfoMapper;
import com.mbti.mindpairing.api.login.repository.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TermsRepository termsRepository;

    @Autowired
    private TermsAgreementRepository termsAgreementRepository;

    @Autowired
    private MbtiRepository mbtiRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private InterestingRepository interestingRepository;

    @Autowired
    private UserMbtiMappingRepository userMbtiMappingRepository;

    @Autowired
    private InterestingUserMappingRepository interestingUserMappingRepository;

    private TermsInfoMapper termsInfoMapper = TermsInfoMapper.INSTANCE;

    private MbtiMapper mbtiMapper = MbtiMapper.INSTANCE;

    private InterestingMapper interestingMapper = InterestingMapper.INSTANCE;

    public List<User.TermsInfoResponse> getTermsInfo() {
        List<TermsEntity> termsEntities = termsRepository.getValidTerms();
        return termsInfoMapper.entitiesToDtos(termsEntities);
    }

    public User.NicknameCheckResponse checkNickname(String nickname) {
        if(userRepository.existenceOfUserByNickname(nickname)) {
            return new User.NicknameCheckResponse(User.ExsistenceYn.Y);
        }else {
            return new User.NicknameCheckResponse(User.ExsistenceYn.N);
        }
    }

    public User.MbtiListResponse getMbtiList() {
        return new User.MbtiListResponse(mbtiMapper.entitiesToDtos(mbtiRepository.findAll()));
    }

    public User.InterestingListResponse getInterestingList() {
        return new User.InterestingListResponse(interestingMapper.entitiesToDtos(interestingRepository.findAll()));
    }


    @Transactional
    public User.RegisterUserResponse registerUser(User.RegisterUserRequest request) throws Exception {
        if(request.getTermsAgreements() == null) {
            throw new MBTIException(CommonCode.NOTERMSAGREEMENT);
        }
        if(userRepository.existenceOfUserByNickname(request.getNickname())) {
            throw new MBTIException(CommonCode.NICKNAME_IS_DUPLICATED);
        }

        // MBTI 적합성 판정
        List<String> mbtiList = mbtiRepository.getMbtiNameList();
        if(!mbtiList.contains(request.getMbti())) {
            throw new MBTIException(CommonCode.MBTI_NOT_EXISTS);
        }

        UserEntity user = new UserEntity(
                null,
                request.getName(),
                request.getNickname(),
                request.getPassword(),
                request.getSex(),
                request.getPhone(),
                request.getMbti(),
                User.UserStatus.REGISTERED,
                null,
                null,
                request.getRole()
        );
        userRepository.save(user);
        for(User.TermsAgreement agreement : request.getTermsAgreements()) {
            TermsAgreementEntity termsAgreementEntity = new TermsAgreementEntity(
                    null,
                    user.getId(),
                    agreement.getTermsId(),
                    agreement.getAgreementYn(),
                    null
            );
            termsAgreementRepository.save(termsAgreementEntity);
        }

        for(Long id : request.getInterestingIdList()) {
            InterestingUserMappingEntity entity = new InterestingUserMappingEntity(
                    null,
                    interestingRepository.getById(id),
                    user
            );
            interestingUserMappingRepository.save(entity);
        }

        for(Long id : request.getMbtiIdList()) {
            UserMbtiMappingEntity entity = new UserMbtiMappingEntity(
                    null,
                    mbtiRepository.getById(id),
                    user
            );
            userMbtiMappingRepository.save(entity);
        }

        User.RegisterUserResponse response = new User.RegisterUserResponse(
                user.getId(),
                user.getName(),
                user.getNickname(),
                user.getSex(),
                user.getMbti(),
                user.getPhone(),
                user.getStatus(),
                user.getRole(),
                mbtiMapper.entitiesToDtos(mbtiRepository.findByUserId(user.getId())),
                interestingMapper.entitiesToDtos(interestingRepository.findByUserId(user.getId()))
        );
        return response;
    }

    public User.PhoneAuthResponse authenticatePhoneNo(User.PhoneAuthRequest body) throws MBTIException {
        UserEntity userEntity = userRepository.findUserByPhoneNumber(body.getPhone());
        if(userEntity == null) {
            return new User.PhoneAuthResponse(body.getPhone());
        }
        if(userEntity.getStatus().equals(User.UserStatus.REGISTERED)) {
            throw new MBTIException(CommonCode.PHONE_IS_DUPLICATED);
        }
        BlackListEntity blackListEntity = blackListRepository.findBlackListById(userEntity.getId());
        if(blackListEntity != null) {
            LocalDateTime blackListDueTime = blackListEntity.getDueDate().plusDays(blackListEntity.getPeriod());
            if(LocalDateTime.now().isBefore(blackListDueTime)) {
                throw new MBTIException(CommonCode.User_Is_A_BlackList);
            }
        }
        return new User.PhoneAuthResponse(body.getPhone());
    }

    public User.PhoneAuthResponse authPhoneVerification(User.PhoneAuthVerificationRequest body) {

        return null;
    }

    public String registerUsingNaverUser(User.RegisterUserRequest body) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://nid.naver.com/oauth2.0/authorize";
        HttpHeaders headers = new HttpHeaders();
        headers.set("response_type", "code");
        headers.set("client_id", "IUKkdY3nqc4D_rM0VCAV");
        headers.set("redirect_uri", "http://127.0.0.1:9001");
        headers.set("state", "state_sample");
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = null;

        response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );
        return response.getBody();
    }

    public String loginUsingKakaoUser(String code) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        String url = "https://kauth.kakao.com/oauth/token";

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", "96cdbc5044b9b9866c31f0792de1c20a");
        map.add("redirenct_uri", "http://127.0.0.1:9001/v1/login/kakao");
        map.add("code", code);
        HttpEntity<MultiValueMap<String, String>> request
                = new HttpEntity<>(map, headers);


        ResponseEntity<KakaoUser.getAccessTokenResponse> response
                = restTemplate.exchange(url, HttpMethod.POST, request, KakaoUser.getAccessTokenResponse.class);
        System.err.println(response);
        System.err.println("Access Token : " + response.getBody().getAccess_token());
        System.err.println("Token Type : " + response.getBody().getToken_type());
        System.err.println("Expires In : " + response.getBody().getExpires_in());
        System.err.println("Refresh Token : " + response.getBody().getRefresh_token());
        System.err.println("scope : " + response.getBody().getScope());
        System.err.println("refresh token expires in : " + response.getBody().getRefresh_token_expires_in());
//        Base64.Decoder decoder = Base64.getDecoder();
//        byte[] decodedBytes = decoder.decode(response.getBody().getId_token());

        System.err.println("============================================================");
//        url = "https://kapi.kakao.com/v2/user/me";
//        headers = new HttpHeaders();
//        headers.set("Authorization", " Bearer " + response.getBody().getAccess_token());
//        request = new HttpEntity(headers);
//
//        ResponseEntity<String> response2 = null;
//
//        response2 = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                request,
//                String.class
//        );
//        System.err.println(response2);
//        System.err.println(response2.getBody());

        System.err.println(getUserInfo(response.getBody().getAccess_token()));
        return "";
    }
    public HashMap<String, Object> getUserInfo (String access_Token) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }

}
