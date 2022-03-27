package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;


import static com.mbti.mindpairing.api.login.entity.QUserEntity.userEntity;

public class UserRepositoryImpl implements UserRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Boolean existenceOfUserByNickname(String nickname) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(userEntity)
                .where(userEntity.nickname.eq(nickname),
                        userEntity.deleteTime.isNull(),
                        userEntity.status.ne(User.UserStatus.DELETED))
                .fetchFirst();
        return fetchOne != null;
    }


    @Override
    public UserEntity findUserByPhoneNumber(String phone) {
        return queryFactory.selectFrom(userEntity)
                .where(userEntity.phone.eq(phone))
                .orderBy(userEntity.id.desc())
                .fetchFirst();
    }
}
