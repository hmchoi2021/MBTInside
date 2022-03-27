package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.BlackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackListEntity, Long>,  BlackListRepositoryCustom{
}
