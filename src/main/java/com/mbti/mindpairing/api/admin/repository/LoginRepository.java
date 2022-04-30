package com.mbti.mindpairing.api.admin.repository;

import com.mbti.mindpairing.api.admin.entity.AdminUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<AdminUserEntity, Long>, LoginRepositoryCustom {
}
