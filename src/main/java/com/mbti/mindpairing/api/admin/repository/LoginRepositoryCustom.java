package com.mbti.mindpairing.api.admin.repository;

import com.mbti.mindpairing.api.admin.entity.AdminUserEntity;

public interface LoginRepositoryCustom {
    AdminUserEntity getIdAndPasswdById(String adminId);
}
