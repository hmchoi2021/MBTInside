package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.UserMbtiMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMbtiMappingRepository extends JpaRepository<UserMbtiMappingEntity, Long>, UserMbtiMappingRepositoryCustom {
}
