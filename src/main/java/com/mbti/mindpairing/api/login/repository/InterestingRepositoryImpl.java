package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.InterestingEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static com.mbti.mindpairing.api.login.entity.QInterestingEntity.interestingEntity;
import static com.mbti.mindpairing.api.login.entity.QInterestingUserMappingEntity.interestingUserMappingEntity;
import static com.mbti.mindpairing.api.login.entity.QUserEntity.userEntity;

import java.util.List;

public class InterestingRepositoryImpl implements InterestingRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;


    @Override
    public List<InterestingEntity> findByUserId(Long userId) {
        return queryFactory.select(interestingEntity)
                .from(interestingEntity)
                .leftJoin(interestingUserMappingEntity)
                .on(interestingEntity.id.eq(interestingUserMappingEntity.interesting.id))
                .leftJoin(userEntity)
                .on(interestingUserMappingEntity.user.id.eq(userEntity.id))
                .where(userEntity.id.eq(userId))
                .fetch();
    }
}
