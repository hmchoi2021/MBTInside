package com.mbti.mindpairing.api.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.exception.MBTIException;
import com.mbti.mindpairing.api.common.http.KakaoAdmin;
import com.mbti.mindpairing.api.login.dto.KakaoUser;
import com.mbti.mindpairing.api.login.dto.Role;
import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.*;
import com.mbti.mindpairing.api.login.mapstruct.InterestingMapper;
import com.mbti.mindpairing.api.login.mapstruct.MbtiMapper;
import com.mbti.mindpairing.api.login.mapstruct.TermsInfoMapper;
import com.mbti.mindpairing.api.login.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    public static final String LOGIN_USER = "LOGIN_USER";

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

    @Autowired
    private KakaoAdmin kakaoAdmin;

    private TermsInfoMapper termsInfoMapper = TermsInfoMapper.INSTANCE;

    private MbtiMapper mbtiMapper = MbtiMapper.INSTANCE;

    private InterestingMapper interestingMapper = InterestingMapper.INSTANCE;

    public List<User.TermsInfoResponse> getTermsInfo(String applicationId) throws MBTIException {
        if(false) throw new MBTIException(CommonCode.APPLICATION_VERSION_IS_NOT_VALID);
        List<TermsEntity> termsEntities = termsRepository.getValidTerms();
        return termsInfoMapper.entitiesToDtos(termsEntities);
    }

    public User.NicknameCheckResponse checkNickname(String applicationId, String nickname) throws MBTIException {
        if(false) throw new MBTIException(CommonCode.APPLICATION_VERSION_IS_NOT_VALID);
        if(userRepository.existenceOfUserByNickname(nickname)) {
            return new User.NicknameCheckResponse(User.ExsistenceYn.Y);
        }else {
            return new User.NicknameCheckResponse(User.ExsistenceYn.N);
        }
    }

    public User.MbtiListResponse getMbtiList(String applicationId) {
        return new User.MbtiListResponse(mbtiMapper.entitiesToDtos(mbtiRepository.findAll()));
    }

    public User.InterestingListResponse getInterestingList(String applicationId) {
        return new User.InterestingListResponse(interestingMapper.entitiesToDtos(interestingRepository.findAll()));
    }


    @Transactional
    public User.RegisterUserResponse registerUser(String applicationId, User.RegisterUserRequest request) throws Exception {
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

    public User.PhoneAuthResponse authenticatePhoneNo(String applicationId, User.PhoneAuthRequest body) throws MBTIException {
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

    public User.PhoneAuthResponse authPhoneVerification(String applicationId, User.PhoneAuthVerificationRequest body) {

        return null;
    }

    public String loginUsingNaverUser(User.RegisterUserRequest body) throws UnsupportedEncodingException {
        String clientId = "IUKkdY3nqc4D_rM0VCAV";//애플리케이션 클라이언트 아이디값";
        String redirectURI = "http://localhost:9001/v1/login/naver/callback";
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + clientId;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&state=" + state;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        response = restTemplate.exchange(
                apiURL,
                HttpMethod.GET,
                request,
                String.class
        );
        System.err.println(response.getBody());
        return response.getBody();
    }

    public String loginUsingNaverCallbackUser(HttpServletRequest request, String code) {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = "https://nid.naver.com";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity req = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        response = restTemplate.exchange(
                apiURL,
                HttpMethod.GET,
                req,
                String.class
        );
        System.err.println(response.getBody());
        return response.getBody();
    }
    @Transactional
    public KakaoUser.kakaoLoginResponse loginUsingKakaoUser(HttpServletRequest request, String code) throws JsonProcessingException {
        KakaoUser.getAccessTokenResponse getAccessTokenResponse = kakaoAdmin.kakaoGetAccessTokenResponse(code);
        Map<String, Object> accountMap = kakaoAdmin.returnAccountMap(getAccessTokenResponse.getAccess_token());
        KakaoUser.kakaoLoginResponse response = new KakaoUser.kakaoLoginResponse();

        boolean ena  = accountMap.get("email_needs_agreement").equals("true") ? true : false;
        boolean arna = accountMap.get("age_range_needs_agreement").equals("true") ? true : false;
        boolean bna  = accountMap.get("birthday_needs_agreement").equals("true") ? true : false;
        boolean gna  = accountMap.get("gender_needs_agreement").equals("true") ? true : false;

        if(!(ena && arna && bna && gna)) {
            String phone = "010" + (int) (Math.random() * 100_000_00);
            UserEntity user = userRepository.findUserByPhoneNumber(phone);
            if(user == null) {
                String name = ((Map<String, String>) accountMap.get("profile")).get("nickname");
                String gender = accountMap.get("gender").equals("male") ? "M" : "F";

                user = new UserEntity(null,
                        name,
                        null,
                        null,
                        gender,
                        phone,
                        null,
                        User.UserStatus.REGISTERED,
                        null,
                        null,
                        Role.USER);
                userRepository.save(user);
            }
            // 등록 후 로그인
            HttpSession session = request.getSession();
            if(session != null) {

            }else{
                session.setAttribute(LOGIN_USER, user);
            }
            response.setStatus(User.LoginStatus.LOGIN);
            response.setUrl(null);
        } else {
                String urls = "https://kauth.kakao.com/oauth/authorize?client_id=96cdbc5044b9b9866c31f0792de1c20a&redirect_uri=http://localhost:9001/v1/login/kakao&response_type=code&scope=";
                if (ena) urls += "account_email,";
                if (arna) urls += "age_range,";
                if (bna) urls += "birthday,";
                if (gna) urls += "gender,";
                if (urls.endsWith(",")) urls = urls.substring(0, urls.length() - 1);

                response.setStatus(User.LoginStatus.NEED_AGREEMENT);
                response.setUrl(urls);
        }
        return response;
    }

    public User.PhoneLoginResonse loginUsingPhoneUser(HttpServletRequest request, String applicationId, User.PhoneAuthVerificationRequest body) throws MBTIException {
        if(false) throw new MBTIException(CommonCode.APPLICATION_VERSION_IS_NOT_VALID);

        HttpSession session = request.getSession();

        // 통신사 로그인 성공 가정
        UserEntity user = userRepository.findUserByPhoneNumber(body.getPhone());

        if(user == null) throw new MBTIException(CommonCode.PHONE_IS_NOT_REGISTERD);

        session.setAttribute(LOGIN_USER, user.getId());
        return new User.PhoneLoginResonse(User.LoginStatus.LOGIN, "");
    }

    public User.PhoneLoginResonse logoutUsingPhoneUser(HttpServletRequest request, String applicationId) throws MBTIException {
        if(false) throw new MBTIException(CommonCode.APPLICATION_VERSION_IS_NOT_VALID);
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();
        return new User.PhoneLoginResonse(User.LoginStatus.LOGOUT, "");
    }

    public List<UserEntity> getUserInfo() {
        return userRepository.findAll();
    }

    public Long getSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(true);
        return (Long) httpSession.getAttribute(LOGIN_USER);

    }
}
