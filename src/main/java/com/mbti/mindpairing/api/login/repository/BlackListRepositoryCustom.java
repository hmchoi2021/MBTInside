package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.BlackListEntity;

public interface BlackListRepositoryCustom {
    BlackListEntity findBlackListById(Long id);
}
