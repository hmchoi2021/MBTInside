package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.InterestingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestingRepository extends JpaRepository<InterestingEntity, Long>, InterestingRepositoryCustom {
    List<InterestingEntity> findAll();
}
