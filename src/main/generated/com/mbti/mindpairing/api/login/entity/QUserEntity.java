package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -1086704655L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deleteTime = createDateTime("deleteTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mbti = createString("mbti");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final EnumPath<com.mbti.mindpairing.api.login.dto.Role> role = createEnum("role", com.mbti.mindpairing.api.login.dto.Role.class);

    public final StringPath sex = createString("sex");

    public final EnumPath<com.mbti.mindpairing.api.login.dto.User.UserStatus> status = createEnum("status", com.mbti.mindpairing.api.login.dto.User.UserStatus.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

