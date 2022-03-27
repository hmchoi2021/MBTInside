package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestingUserMappingEntity is a Querydsl query type for InterestingUserMappingEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestingUserMappingEntity extends EntityPathBase<InterestingUserMappingEntity> {

    private static final long serialVersionUID = -1172194223L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestingUserMappingEntity interestingUserMappingEntity = new QInterestingUserMappingEntity("interestingUserMappingEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInterestingEntity interesting;

    public final QUserEntity user;

    public QInterestingUserMappingEntity(String variable) {
        this(InterestingUserMappingEntity.class, forVariable(variable), INITS);
    }

    public QInterestingUserMappingEntity(Path<? extends InterestingUserMappingEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterestingUserMappingEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterestingUserMappingEntity(PathMetadata metadata, PathInits inits) {
        this(InterestingUserMappingEntity.class, metadata, inits);
    }

    public QInterestingUserMappingEntity(Class<? extends InterestingUserMappingEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interesting = inits.isInitialized("interesting") ? new QInterestingEntity(forProperty("interesting")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user")) : null;
    }

}

