package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.TermsAgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsAgreementRepository extends JpaRepository<TermsAgreementEntity, Long>, TermsAgreementRepositoryCustom{
}
