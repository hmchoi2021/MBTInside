package com.mbti.mindpairing.api.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommunityDto {
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetCommunityHomeRequest {
        @Schema(description = "어드민 id", defaultValue = "admin", nullable = false)
        private String id;
        @Schema(description = "어드민 비번", defaultValue = "admin", nullable = false)
        private String hashed_passwd;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetCommunityHomeResponse {
        @Schema(description = "어드민 id", defaultValue = "admin", nullable = false)
        private String id;
        @Schema(description = "어드민 비번", defaultValue = "admin", nullable = false)
        private String hashed_passwd;
    }
}
