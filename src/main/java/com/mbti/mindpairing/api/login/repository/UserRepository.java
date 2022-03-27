package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom{
}
