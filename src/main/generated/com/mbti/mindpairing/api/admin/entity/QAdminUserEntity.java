package com.mbti.mindpairing.api.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminUserEntity is a Querydsl query type for AdminUserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminUserEntity extends EntityPathBase<AdminUserEntity> {

    private static final long serialVersionUID = -1909705964L;

    public static final QAdminUserEntity adminUserEntity = new QAdminUserEntity("adminUserEntity");

    public final StringPath adminId = createString("adminId");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deleteTime = createDateTime("deleteTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final EnumPath<com.mbti.mindpairing.api.login.dto.User.UserStatus> status = createEnum("status", com.mbti.mindpairing.api.login.dto.User.UserStatus.class);

    public QAdminUserEntity(String variable) {
        super(AdminUserEntity.class, forVariable(variable));
    }

    public QAdminUserEntity(Path<? extends AdminUserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminUserEntity(PathMetadata metadata) {
        super(AdminUserEntity.class, metadata);
    }

}

