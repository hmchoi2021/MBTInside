package com.mbti.mindpairing.api.common.dto;

import com.mbti.mindpairing.api.common.constant.CommonCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class ApiResponse<T> {


    @Getter
    @Setter
    @Schema(description = "응답시각", defaultValue = "2022-03-06T00:57:50.498417", nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Getter
    @Setter
    @Schema(description = "에러메시지", defaultValue = "There is a problem in internal server", nullable = false)
    private String errorMsg;

    @Getter
    @Setter
    @Schema(description = "에러코드", defaultValue = "server-internal", nullable = false)
    private String errorCode;

    @Getter
    @Setter
    @Schema(description = "Http상태코드", defaultValue = "500", nullable = false)
    private int httpStatus;

    @Getter
    @Setter
    private T data;

    public ApiResponse(CommonCode code) {
        this.httpStatus = code.getStatus();
        this.errorCode  = code.getCode();
        this.errorMsg   = code.getMsg();
        this.data       = null;
    }

    public ApiResponse(CommonCode code, T data) {
        this.httpStatus = code.getStatus();
        this.errorCode  = code.getCode();
        this.errorMsg   = code.getMsg();
        this.data       = data;
    }
}
