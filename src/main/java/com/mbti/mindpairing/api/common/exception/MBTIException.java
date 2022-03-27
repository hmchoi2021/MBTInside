package com.mbti.mindpairing.api.common.exception;

import com.mbti.mindpairing.api.common.constant.CommonCode;
import lombok.Getter;

@Getter
public class MBTIException extends Exception{

    private CommonCode code;

    public MBTIException(CommonCode code) {
        super(code.getMsg());
        this.code = code;
    }
}
