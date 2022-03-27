package com.mbti.mindpairing.api.login.controller;

import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.dto.ApiResponse;
import com.mbti.mindpairing.api.common.exception.MBTIException;
import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
            HttpServletRequest request
    ) {
        return new ApiResponse<List<User.TermsInfoResponse>>(CommonCode.SUCCESS, loginService.getTermsInfo());
    }

    @Operation(summary = "닉네임 중복 조회", description = "닉네임 중복 조회")
    @ResponseBody
    @RequestMapping(value = "/v1/nickname/{nickname}", method = RequestMethod.GET)
    public ApiResponse<User.NicknameCheckResponse> v1NicknameCheckGet(
            HttpServletRequest request,
            @Parameter(name = "nickname", example = "testname", required = true)
            @PathVariable(value = "nickname") String nickname) {
        User.NicknameCheckResponse nicknameCheckResponse = loginService.checkNickname(nickname);
        return new ApiResponse<User.NicknameCheckResponse>(CommonCode.SUCCESS, nicknameCheckResponse);
    }

    @Operation(summary = "휴대폰 번호 인증", description = "휴대폰 번호 전달")
    @ResponseBody
    @PostMapping("/v1/auth")
    public ApiResponse<User.PhoneAuthResponse> v1AuthPhonePost(
            HttpServletRequest request,
            @RequestBody User.PhoneAuthRequest body) throws MBTIException {
        return new ApiResponse<User.PhoneAuthResponse>(CommonCode.SUCCESS, loginService.authenticatePhoneNo(body));
    }

    @Operation(summary = "휴대폰 번호 인증 검증", description = "휴대폰 번호 인증 결과 전달")
    @ResponseBody
    @PostMapping("/v1/auth/verification")
    public ApiResponse<User.PhoneAuthResponse> v1AuthPhoneVerificationPost(
            HttpServletRequest request,
            @RequestBody User.PhoneAuthVerificationRequest body) throws MBTIException {
        return new ApiResponse<User.PhoneAuthResponse>(CommonCode.SUCCESS, loginService.authPhoneVerification(body));
    }


    @Operation(summary = "회원 가입", description = "[기본] 회원 가입")
    @ResponseBody
    @PostMapping("/v1/registration")
    public ApiResponse<User.RegisterUserResponse> v1RegistrationPost(
            HttpServletRequest request,
            @RequestBody User.RegisterUserRequest body) throws Exception {
        return new ApiResponse<User.RegisterUserResponse>(CommonCode.SUCCESS, loginService.registerUser(body));
    }

    @Operation(summary = "MBTI List 조회", description = "[기본] 회원 가입시 mbti 리스트 조회")
    @ResponseBody
    @PostMapping("/v1/mbti")
    public ApiResponse<User.MbtiListResponse> v1MbtiListGet(
            HttpServletRequest request) throws Exception {
        return new ApiResponse<User.MbtiListResponse>(CommonCode.SUCCESS, loginService.getMbtiList());
    }

    @Operation(summary = "관심사 List 조회", description = "[기본] 회원 가입시 관심사 리스트 조회")
    @ResponseBody
    @PostMapping("/v1/interesting")
    public ApiResponse<User.InterestingListResponse> v1InterestingListGet(
            HttpServletRequest request) throws Exception {
        return new ApiResponse<User.InterestingListResponse>(CommonCode.SUCCESS, loginService.getInterestingList());
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
    public ApiResponse<String> v1LoginUsingKakaoGET(
            HttpServletRequest request,
            @RequestParam("code") String code) throws MBTIException {
            System.err.println("code : " + code);
        return new ApiResponse<String>(CommonCode.SUCCESS, loginService.loginUsingKakaoUser(code));
    }
}
