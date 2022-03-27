package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserMbtiMappingEntity is a Querydsl query type for UserMbtiMappingEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserMbtiMappingEntity extends EntityPathBase<UserMbtiMappingEntity> {

    private static final long serialVersionUID = 439799513L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserMbtiMappingEntity userMbtiMappingEntity = new QUserMbtiMappingEntity("userMbtiMappingEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMbtiEntity mbti;

    public final QUserEntity user;

    public QUserMbtiMappingEntity(String variable) {
        this(UserMbtiMappingEntity.class, forVariable(variable), INITS);
    }

    public QUserMbtiMappingEntity(Path<? extends UserMbtiMappingEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserMbtiMappingEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserMbtiMappingEntity(PathMetadata metadata, PathInits inits) {
        this(UserMbtiMappingEntity.class, metadata, inits);
    }

    public QUserMbtiMappingEntity(Class<? extends UserMbtiMappingEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mbti = inits.isInitialized("mbti") ? new QMbtiEntity(forProperty("mbti")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user")) : null;
    }

}

