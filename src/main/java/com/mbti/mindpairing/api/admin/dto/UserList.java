package com.mbti.mindpairing.api.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserList {
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getAdminLoginRequest {
        private String id;
        private String hashed_passwd;
    }
}
