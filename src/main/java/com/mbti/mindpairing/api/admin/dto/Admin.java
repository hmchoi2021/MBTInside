package com.mbti.mindpairing.api.admin.dto;

import com.mbti.mindpairing.api.login.dto.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class Admin {
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetAdminLoginRequest {
        @Schema(description = "어드민 id", defaultValue = "admin", nullable = false)
        private String id;
        @Schema(description = "어드민 비번", defaultValue = "admin", nullable = false)
        private String hashed_passwd;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetAdminLoginResponse {
        @Schema(description = "로그인/로그아웃 상태", defaultValue = "LOGIN", nullable = false)
        private User.LoginStatus status;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfoRequest {
        @Schema(description = "nickname", defaultValue = "haha", nullable = true)
        private String nickname;
        @Schema(description = "mbti", defaultValue = "INTP", nullable = true)
        private Long mbti;
        @Schema(description = "blacklist", nullable = true)
        private boolean blackList;
        @Schema(description = "male", nullable = true)
        private boolean male;
        @Schema(description = "female", nullable = true)
        private boolean female;
        @Schema(description = "notConnected", nullable = true)
        private boolean notConnected;
        @Schema(description = "accountWithdrawal", nullable = true)
        private boolean accountWithdrawal;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfo {
        @Schema(description = "사용자 id", nullable = false)
        private Long userId;
        @Schema(description = "닉네임", nullable = false)
        private String nickname;
        @Schema(description = "유형", nullable = false)
        private String mbti;
        @Schema(description = "작성글수", nullable = false)
        private int numOfPosts;
        @Schema(description = "댓글 갯수", nullable = false)
        private int numOfComments;
        @Schema(description = "블랙리스트 기간", nullable = false)
        private int periodOfBlackList;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfoList {
        @Schema(description = "사용자리스트", nullable = false)
        private List<UserInfo> userInfos;
        @Schema(description = "가입회원명 수", nullable = false)
        private Long numOfMember;
        @Schema(description = "새로운글 수", nullable = false)
        private Long numOfNewPosts;
        @Schema(description = "접속자 수", nullable = false)
        private Long NumOfAccessors;
        @Schema(description = "총 목록 수", nullable = false)
        private Long total;
        @Schema(description = "page size", nullable = false)
        private int pageSize;
        @Schema(description = "page no", nullable = false)
        private int pageNo;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userDetailInfoRequest {
        private Long userId;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userDetailInfo {
        private String nickName;
        private String mbti;
        private Long age;
        private String Sex;
        private Long numOfPosts;
        private Long numOfComments;
        private Long numOfReports;
        private Long numOfReported;
        private LocalDateTime createTime;
        private LocalDateTime deletedTime;
        private String phone;

    }



}
