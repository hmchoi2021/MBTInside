package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.BlackListEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static com.mbti.mindpairing.api.login.entity.QBlackListEntity.blackListEntity;

public class BlackListRepositoryImpl implements BlackListRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public BlackListEntity findBlackListById(Long id) {
        return queryFactory.selectFrom(blackListEntity)
                .where(blackListEntity.userId.eq(id))
                .orderBy(blackListEntity.id.desc())
                .fetchFirst();
    }
}
