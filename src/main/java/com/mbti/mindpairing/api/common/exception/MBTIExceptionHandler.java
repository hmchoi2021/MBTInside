package com.mbti.mindpairing.api.common.exception;

import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MBTIExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MBTIExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> exceptionHandle(Exception e) {
        CommonCode code = CommonCode.SERVERINTERNAL;
        if(e instanceof MBTIException) {
            code = ((MBTIException) e).getCode();
        }
        if(code == CommonCode.SERVERINTERNAL) {
            code.setMsg(e.getMessage());
        }
        LOGGER.error(e.getMessage());
        return new ApiResponse<>(code, null);
    }
}
