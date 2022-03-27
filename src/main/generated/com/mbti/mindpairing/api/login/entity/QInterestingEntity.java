package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInterestingEntity is a Querydsl query type for InterestingEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestingEntity extends EntityPathBase<InterestingEntity> {

    private static final long serialVersionUID = 1330470072L;

    public static final QInterestingEntity interestingEntity = new QInterestingEntity("interestingEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QInterestingEntity(String variable) {
        super(InterestingEntity.class, forVariable(variable));
    }

    public QInterestingEntity(Path<? extends InterestingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInterestingEntity(PathMetadata metadata) {
        super(InterestingEntity.class, metadata);
    }

}

