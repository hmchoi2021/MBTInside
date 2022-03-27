package com.mbti.mindpairing.api.login.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class InterestingUserMappingRepositoryImpl implements InterestingUserMappingRepositoryCustom{
    @Autowired
    private JPAQueryFactory queryFactory;
}
