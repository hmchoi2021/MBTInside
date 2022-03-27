package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.TermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepository extends JpaRepository<TermsEntity, Long>, TermsRepositoryCustom{
}
