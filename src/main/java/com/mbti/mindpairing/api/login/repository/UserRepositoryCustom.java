package com.mbti.mindpairing.api.login.repository;
import com.mbti.mindpairing.api.login.entity.UserEntity;

public interface UserRepositoryCustom {
    Boolean existenceOfUserByNickname(String nickname);

    UserEntity findUserByPhoneNumber(String phone);
}
