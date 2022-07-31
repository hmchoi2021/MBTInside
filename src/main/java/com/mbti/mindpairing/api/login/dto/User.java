package com.mbti.mindpairing.api.login.dto;

import com.mbti.mindpairing.api.login.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.Map;

public class User {

    public enum TermsStatus {
        VALID, INVALID
    }

    public enum ExsistenceYn {
        Y, N
    }
    public enum UserStatus {
        REGISTERED, DELETED, BLACKLIST
    }

    public enum LoginStatus {
        REGISTERED, LOGIN, LOGOUT, NEED_AGREEMENT, NOT_EXISTS_PHONE_NO, NOT_VALID_APPLICATION, NOT_REGISTERED
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NicknameCheckResponse {
        private ExsistenceYn exists;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsInfoResponse {
        private Long   id;
        private String title;
        private String termsInfo;
        private String startTime;
        private String mandatoryYn;
        private String status;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneAuthRequest {
        @Schema(description = "이름", defaultValue = "홍길동", nullable = false)
        private String name;

        @Schema(description = "통신사", defaultValue = "SKT", nullable = false)
        private String provider;

        @Schema(description = "전화번호", defaultValue = "01012341234", nullable = false)
        private String phone;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneAuthResponse {
        @Schema(description = "전화번호", defaultValue = "01012341234", nullable = false)
        private String phone;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneAuthVerificationRequest {
        @Schema(description = "전화번호", defaultValue = "01012341234", nullable = false)
        private String phone;

        @Schema(description = "전화번호 인증번호", defaultValue = "123456", nullable = false)
        private String verificationNo;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneAuthVerificationResponse {
        private String phone;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Mbti {
        @Schema(description = "mbti Id", defaultValue = "1", nullable = false)
        private Long id;

        @Schema(description = "mbti name", defaultValue = "ESFJ", nullable = false)
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MbtiListResponse {
        @Schema(description = "mbti 리스트", nullable = false)
        private List<Mbti> mbtiList;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Interesting {
        @Schema(description = "interesting Id", defaultValue = "1", nullable = false)
        private Long id;

        @Schema(description = "interesting name", defaultValue = "연애", nullable = false)
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InterestingListResponse {
        @Schema(description = "관심사 리스트", nullable = false)
        private List<Interesting> interestingList;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "사용자등록요청")
    public static class RegisterUserRequest {

        @Schema(description = "이름", defaultValue = "홍길동", nullable = false)
        private String name;

        @Schema(description = "닉네임", defaultValue = "mbtimania", nullable = false)
        private String nickname;

        @Schema(description = "패스워드(추후에 FE에서 단방향 암호화해서 전달 필요)", defaultValue = "qwer1234!", nullable = false)
        private String password;

        @Schema(description = "성별", defaultValue = "M", nullable = false, allowableValues = {"M", "W"})
        private String sex;

        @Schema(description = "전화번호", defaultValue = "01012341234", nullable = false)
        private String phone;

        @Schema(description = "MBTI", defaultValue = "INTP", nullable = false)
        private String mbti;

        @Schema(description = "역할", defaultValue = "GUEST", nullable = false, allowableValues = {"GUEST", "ADMIN"})
        private Role Role;

        @Schema(description = "약관동의")
        private List<TermsAgreement> termsAgreements;

        @Schema(description = "관심있는 MBTI id 리스트", defaultValue = "[1,3,5,6]", nullable = false)
        private List<Long> mbtiIdList;

        @Schema(description = "관심사 id 리스트", defaultValue = "[1,3,5,6]", nullable = false)
        private List<Long> interestingIdList;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "약관동의")
    public static class TermsAgreement {
        @Schema(description = "약관아이디", defaultValue = "1", nullable = false)
        private Long   termsId;
        @Schema(description = "약관동의", defaultValue = "Y", nullable = false, allowableValues = {"Y", "N"})
        private String agreementYn;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterUserResponse {
        private Long   id;

        @Schema(description = "이름", defaultValue = "홍길동", nullable = false)
        private String name;

        @Schema(description = "닉네임", defaultValue = "mbtimania", nullable = false)
        private String nickname;

        @Schema(description = "패스워드", defaultValue = "qwer1234!", nullable = false)
        private String sex;

        @Schema(description = "전화번호", defaultValue = "01012341234", nullable = false)
        private String phone;

        @Schema(description = "MBTI", defaultValue = "INTP", nullable = false)
        private String mbti;

        @Schema(description = "상태", defaultValue = "REGISTERED", nullable = false)
        private User.UserStatus status;

        @Schema(description = "역할", defaultValue = "GUEST", nullable = false, allowableValues = {"GUEST", "ADMIN"})
        private Role Role;

        private List<Mbti> mbtiList;

        private List<Interesting> interestingList;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneLoginResonse {
        @Schema(description = "로그인 상태", defaultValue = "LOGINED", nullable = false)
        private LoginStatus status;

        @Schema(description = "sessionId", defaultValue = "zz0101xx-bab9-4b92-9b32-dadb280f4b61")
        private String sessionId;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfoList {
        @Schema(description = "로그인 상태", defaultValue = "LOGINED", nullable = false)
        private List<UserEntity> userEntitiesList;

        @Schema(description = "total", defaultValue = "1")
        private Integer total;

        @Schema(description = "pageNo", defaultValue = "1")
        private Integer pageNo;

        @Schema(description = "pageSize", defaultValue = "10")
        private Integer pageSize;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMBTITestEvaluateRequest {
        @Schema(description = "test result Map", defaultValue = "{(1,2), (2,3), (3,2), ...}", nullable = false)
        private Map<Integer, Integer> testResultMap;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMBTITestEvaluateResponse {
        @Schema(description = "사용자 Id", defaultValue = "1", nullable = false)
        private Long userId;

        @Schema(description = "MBTI", defaultValue = "ENTP", nullable = false)
        private String mbti;

    }
}
