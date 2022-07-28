package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

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
                .where(userEntity.phone.eq(phone), userEntity.deleteTime.isNull())
                .orderBy(userEntity.id.desc())
                .fetchFirst();
    }

    @Override
    public UserEntity findUserByUserId(Long userId) {
        return queryFactory.selectFrom(userEntity)
                .where(userEntity.id.eq(userId), userEntity.deleteTime.isNull())
                .orderBy(userEntity.id.desc())
                .fetchFirst();
    }

    @Override
    public UserEntity findByNickName(String nickName) {
        return queryFactory.selectFrom(userEntity)
                .where(userEntity.nickname.eq(nickName), userEntity.deleteTime.isNull())
                .orderBy(userEntity.id.desc())
                .fetchFirst();
    }

    @Override
    public int countActiveUser() {
        return queryFactory.selectFrom(userEntity)
                .where(userEntity.deleteTime.isNull())
                .fetch().size();
    }

    @Override
    public List<UserEntity> findAllUser(int pageNo, int pageSize) {
        JPAQuery<UserEntity> query = queryFactory.selectFrom(userEntity)
                .where(userEntity.deleteTime.isNull())
                .orderBy(userEntity.id.desc());
        if(pageSize > 0) {
            pageNo --;
            query = query.offset(pageNo*pageSize);
            query = query.limit(pageSize);
        }
        return query.fetch();
    }
}
