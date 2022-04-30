package com.mbti.mindpairing.api.admin.repository;

import com.mbti.mindpairing.api.admin.entity.AdminUserEntity;
import com.mbti.mindpairing.api.login.dto.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static com.mbti.mindpairing.api.admin.entity.QAdminUserEntity.adminUserEntity;
public class LoginRepositoryImpl implements LoginRepositoryCustom{
    @Autowired
    private JPAQueryFactory queryFactory;


    @Override
    public AdminUserEntity getIdAndPasswdById(String adminId) {
        return queryFactory.selectFrom(adminUserEntity)
                .where(adminUserEntity.adminId.eq(adminId),
                        adminUserEntity.createTime.isNotNull(),
                        adminUserEntity.deleteTime.isNull(),
                        adminUserEntity.status.eq(User.UserStatus.REGISTERED))
                .fetchOne();

    }
}
