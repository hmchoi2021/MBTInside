package com.mbti.mindpairing.api.common.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum CommonCode {
    // Basic Code
    SUCCESS(200, null, null),
    SERVERINTERNAL(500, "server-internal", "There is a problem in internal server"),
    APPLICATION_VERSION_IS_NOT_VALID(400, "application-001", "The application version is not valid"),
    // Register Related Code
    NOTERMSAGREEMENT(400, "register-001", "There are no agreements of terms"),
    NICKNAME_IS_DUPLICATED(400, "register-002", "The nickname is duplicated"),
    MBTI_NOT_EXISTS(400, "register-003", "mbti is not valid"),
    PHONE_IS_DUPLICATED(400, "register-004", "The phone number is duplicated"),
    User_Is_A_BlackList(400, "register-005", "The user with the phone number is a black list"),
    PHONE_IS_NOT_REGISTERD(400, "register-006", "The phone number is not registered"),
    USER_IS_NOT_EXISTED(400, "register-007", "The user is not existed"),
    USER_TEST_LIST_SIZE_INVALIDATED(400, "register-008", "The user's test list length is not equal to 20"),

    // Login Related Code
    ALREADY_LOGINED(400, "login-001", "The user is already logined"),
    NOT_LOGINED(400, "login-002", "The user is not logined"),
    USER_IS_NOT_VALID(400, "login-003", "The user's id or password is not valid");


    private final int status;
    private String code;
    private String msg;

    private static final Map<String, CommonCode> codeMap = new HashMap<>();
    static{
        for(CommonCode code : values()) {
            codeMap.put(code.name(), code);
        }
    }
    CommonCode(final int status, final String code, final String msg) {
        this.status = status;
        this.code   = code;
        this.msg    = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
