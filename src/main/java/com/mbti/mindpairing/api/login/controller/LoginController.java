package com.mbti.mindpairing.api.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.dto.ApiResponse;
import com.mbti.mindpairing.api.common.exception.MBTIException;
import com.mbti.mindpairing.api.login.dto.KakaoUser;
import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.UserEntity;
import com.mbti.mindpairing.api.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/*
    Author : Min
 */
@Tag(name = "Login API v1")
@RestController
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Operation(summary = "약관 조회", description = "약관 리스트 반환")
    @ResponseBody
    @GetMapping("/v1/terms")
    public ApiResponse<List<User.TermsInfoResponse>> v1TermsGet(
            HttpServletRequest request,
            @RequestParam("application_id") String applicationId
    ) throws MBTIException {
        return new ApiResponse<List<User.TermsInfoResponse>>(CommonCode.SUCCESS, loginService.getTermsInfo(applicationId));
    }

    @Operation(summary = "닉네임 중복 조회", description = "닉네임 중복 조회")
    @ResponseBody
    @RequestMapping(value = "/v1/nickname/{nickname}", method = RequestMethod.GET)
    public ApiResponse<User.NicknameCheckResponse> v1NicknameCheckGet(
            HttpServletRequest request,
            @RequestParam("application_id") String applicationId,
            @Parameter(name = "nickname", example = "testname", required = true)
            @PathVariable(value = "nickname") String nickname) throws MBTIException {
        User.NicknameCheckResponse nicknameCheckResponse = loginService.checkNickname(applicationId, nickname);
        return new ApiResponse<User.NicknameCheckResponse>(CommonCode.SUCCESS, nicknameCheckResponse);
    }

    @Operation(summary = "휴대폰 번호 인증", description = "휴대폰 번호 전달")
    @ResponseBody
    @PostMapping("/v1/auth")
    public ApiResponse<User.PhoneAuthResponse> v1AuthPhonePost(
            HttpServletRequest request,
            @RequestParam("application_id") String applicationId,
            @RequestBody User.PhoneAuthRequest body) throws MBTIException {
        return new ApiResponse<User.PhoneAuthResponse>(CommonCode.SUCCESS, loginService.authenticatePhoneNo(applicationId, body));
    }

    @Operation(summary = "휴대폰 번호 인증 검증", description = "휴대폰 번호 인증 결과 전달")
    @ResponseBody
    @PostMapping("/v1/auth/verification")
    public ApiResponse<User.PhoneAuthResponse> v1AuthPhoneVerificationPost(
            HttpServletRequest request,
            @RequestParam("application_id") String applicationId,
            @RequestBody User.PhoneAuthVerificationRequest body) throws MBTIException {
        return new ApiResponse<User.PhoneAuthResponse>(CommonCode.SUCCESS, loginService.authPhoneVerification(applicationId, body));
    }


    @Operation(summary = "회원 가입", description = "[기본] 회원 가입")
    @ResponseBody
    @PostMapping("/v1/registration")
    public ApiResponse<User.RegisterUserResponse> v1RegistrationPost(
            HttpServletRequest request,
            @RequestParam("application_id") String applicationId,
            @RequestBody User.RegisterUserRequest body) throws Exception {
        return new ApiResponse<User.RegisterUserResponse>(CommonCode.SUCCESS, loginService.registerUser(applicationId, body));
    }

    @Operation(summary = "MBTI List 조회", description = "[기본] 회원 가입시 mbti 리스트 조회")
    @ResponseBody
    @GetMapping("/v1/mbti")
    public ApiResponse<User.MbtiListResponse> v1MbtiListGet(
            @RequestParam("application_id") String applicationId,
            HttpServletRequest request) throws Exception {
        return new ApiResponse<User.MbtiListResponse>(CommonCode.SUCCESS, loginService.getMbtiList(applicationId));
    }

    @Operation(summary = "관심사 List 조회", description = "[기본] 회원 가입시 관심사 리스트 조회")
    @ResponseBody
    @GetMapping("/v1/interesting")
    public ApiResponse<User.InterestingListResponse> v1InterestingListGet(
            @RequestParam("application_id") String applicationId,
            HttpServletRequest request){
        return new ApiResponse<User.InterestingListResponse>(CommonCode.SUCCESS, loginService.getInterestingList(applicationId));
    }

//    @Operation(summary = "Naver Test", description = "[네이버] 회원 가입")
//    @ResponseBody
//    @RequestMapping(value = "/v1/naver/registration", method = RequestMethod.POST)
//    public ApiResponse<String> v1RegistrationUsingNaverPost(
//            HttpServletRequest request,
//            @RequestBody User.RegisterUserRequest body) throws MBTIException {
//        return new ApiResponse<String>(CommonCode.SUCCESS, loginService.registerUsingNaverUser(body));
//    }

//    @Operation(summary = "Naver Test Redirect", description = "[네이버] 회원 가입")
//    @ResponseBody
//    @RequestMapping(value = "/v1/naver/redirect", method = RequestMethod.GET)
//    public ApiResponse<String> v1RegistrationUsingNaverGET(
//            HttpServletRequest request,
//            @RequestBody User.RegisterUserRequest body) throws MBTIException {
//        return new ApiResponse<String>(CommonCode.SUCCESS, loginService.registerUsingNaverUser(body));
//    }

    @Operation(summary = "Kakao Login Test", description = "[카카오] 로그인")
    @ResponseBody
    @RequestMapping(value = "/v1/login/kakao", method = RequestMethod.GET)
    public ApiResponse<KakaoUser.kakaoLoginResponse> v1KakaoLoginGET(
            HttpServletRequest request,
            @RequestParam("code") String code) throws JsonProcessingException {
        return new ApiResponse<KakaoUser.kakaoLoginResponse>(CommonCode.SUCCESS, loginService.loginUsingKakaoUser(request, code));
    }

    @Operation(summary = "Naver Login Test", description = "[네이버] 로그인")
    @ResponseBody
    @RequestMapping(value = "/v1/login/naver", method = RequestMethod.GET)
    public ApiResponse<String> v1NaverLoginGET(
            HttpServletRequest request) throws UnsupportedEncodingException {
        return new ApiResponse<String>(CommonCode.SUCCESS, loginService.loginUsingNaverUser(null));
    }

    @Operation(summary = "Naver Login Callback", description = "[네이버] 로그인 callback")
    @ResponseBody
    @RequestMapping(value = "/v1/login/naver/callback", method = RequestMethod.GET)
    public ApiResponse<String> v1NaverLoginCallbackGET(
            HttpServletRequest request,
            @RequestParam("code") String code) throws UnsupportedEncodingException {
        return new ApiResponse<String>(CommonCode.SUCCESS, loginService.loginUsingNaverCallbackUser(request, code));
    }

    @Operation(summary = "Login", description = "[일반] 휴대폰 로그인, 성공 시 리턴값은 sessioId")
    @ResponseBody
    @RequestMapping(value = "/v1/login/phone", method = RequestMethod.POST)
    public ApiResponse<User.PhoneLoginResonse> v1PhoneLoginPOST(
            HttpServletRequest request,
            @RequestParam("application_id") String applicationId,
            @RequestBody User.PhoneAuthVerificationRequest body) throws MBTIException {
        return new ApiResponse<User.PhoneLoginResonse>(CommonCode.SUCCESS, loginService.loginUsingPhoneUser(request, applicationId, body));
    }

    @Operation(summary = "Loginout", description = "[일반] 휴대폰 로그아웃")
    @ResponseBody
    @RequestMapping(value = "/v1/logout/phone", method = RequestMethod.POST)
    public ApiResponse<User.PhoneLoginResonse> v1PhoneLogoutPOST(
            HttpServletRequest request,
            @RequestParam("application_id") String applicationId) throws MBTIException {
        return new ApiResponse<User.PhoneLoginResonse>(CommonCode.SUCCESS, loginService.logoutUsingPhoneUser(request, applicationId));
    }


    @Operation(summary = "[TEST용] 등록된 유저 조회", description = "[TEST용] 등록된 유저 조회(실제사용 X)")
    @ResponseBody
    @GetMapping("/v1/userInfo")
    public ApiResponse<List<UserEntity>> v1UsersGet(
            HttpServletRequest request
    ){
        return new ApiResponse<List<UserEntity>>(CommonCode.SUCCESS, loginService.getUserInfo());
    }

    @Operation(summary = "[TEST용] 세션확인", description = "[TEST용] 유저 확인")
    @ResponseBody
    @GetMapping("/v1/getSession")
    public ApiResponse<Long> v1SessionGet(
            HttpServletRequest request
    ){
        return new ApiResponse<Long>(CommonCode.SUCCESS, loginService.getSession(request));
    }
}
