package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.TermsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.mbti.mindpairing.api.login.entity.QTermsEntity.termsEntity;

public class TermsRepositoryImpl implements TermsRepositoryCustom{
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<TermsEntity> getValidTerms() {
        return queryFactory
                .selectFrom(termsEntity)
                .where(termsEntity.status.eq(User.TermsStatus.VALID))
                .fetch();
    }
}
