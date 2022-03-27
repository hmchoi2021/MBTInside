package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.MbtiEntity;

import java.util.List;

public interface MbtiRepositoryCustom {
    List<String> getMbtiNameList();

    public List<MbtiEntity> findByUserId(Long userId);
}
