package com.mbti.mindpairing.api.login.dto;

import lombok.*;

public class KakaoUser {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getAccessTokenResponse {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private String id_token;
        private String expires_in;
        private String scope;
        private Integer refresh_token_expires_in;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getAgreement {
        private String scope;
        private Boolean email_needs_agreement;
        private Boolean age_range_needs_agreement;
        private Boolean birthday_needs_agreement;
        private Boolean gender_needs_agreement;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class kakaoLoginResponse {
        private User.LoginStatus status;
        private String url;
        private String sessionId;
    }
}

