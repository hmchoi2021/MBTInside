package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.TermsEntity;

import java.util.List;

public interface TermsRepositoryCustom {
    List<TermsEntity> getValidTerms();
}
