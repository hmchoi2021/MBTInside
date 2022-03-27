package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.InterestingEntity;

import java.util.List;

public interface InterestingRepositoryCustom {
    List<InterestingEntity> findByUserId(Long userId);
}
