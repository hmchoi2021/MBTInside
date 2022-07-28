package com.mbti.mindpairing.api.login.repository;
import com.mbti.mindpairing.api.login.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
    Boolean existenceOfUserByNickname(String nickname);
    UserEntity findUserByPhoneNumber(String phone);
    UserEntity findUserByUserId(Long userId);
    UserEntity findByNickName(String nickName);
    int countActiveUser();
    List<UserEntity> findAllUser(int pageNo, int pageSize);
}
