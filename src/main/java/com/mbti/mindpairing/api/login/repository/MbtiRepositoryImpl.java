package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.MbtiEntity;
import com.mbti.mindpairing.api.login.mapstruct.MbtiMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import static com.mbti.mindpairing.api.login.entity.QMbtiEntity.mbtiEntity;
import static com.mbti.mindpairing.api.login.entity.QUserMbtiMappingEntity.userMbtiMappingEntity;
import static com.mbti.mindpairing.api.login.entity.QUserEntity.userEntity;

import java.util.List;

public class MbtiRepositoryImpl implements MbtiRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<String> getMbtiNameList() {
        return queryFactory.select(mbtiEntity.name)
                .from(mbtiEntity)
                .orderBy(mbtiEntity.id.asc())
                .fetch();
    }

    @Override
    public List<MbtiEntity> findByUserId(Long userId) {
        return queryFactory.select(mbtiEntity)
                .from(mbtiEntity)
                .leftJoin(userMbtiMappingEntity)
                .on(mbtiEntity.id.eq(userMbtiMappingEntity.mbti.id))
                .leftJoin(userEntity)
                .on(userMbtiMappingEntity.user.id.eq(userEntity.id))
                .where(userEntity.id.eq(userId))
                .fetch();
    }
}
