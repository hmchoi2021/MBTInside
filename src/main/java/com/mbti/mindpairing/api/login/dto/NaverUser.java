package com.mbti.mindpairing.api.login.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NaverUser {
    private String name;
    private String email;

    @Builder
    public NaverUser(String name, String email) {
        this.name  = name;
        this.email = email;
    }
}
