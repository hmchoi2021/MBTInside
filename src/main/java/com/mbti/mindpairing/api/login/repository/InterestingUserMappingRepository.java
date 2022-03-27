package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.InterestingUserMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestingUserMappingRepository extends JpaRepository<InterestingUserMappingEntity, Long>,  InterestingUserMappingRepositoryCustom{

}
