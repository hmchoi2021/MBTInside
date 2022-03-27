package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMbtiEntity is a Querydsl query type for MbtiEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbtiEntity extends EntityPathBase<MbtiEntity> {

    private static final long serialVersionUID = 2122110896L;

    public static final QMbtiEntity mbtiEntity = new QMbtiEntity("mbtiEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QMbtiEntity(String variable) {
        super(MbtiEntity.class, forVariable(variable));
    }

    public QMbtiEntity(Path<? extends MbtiEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMbtiEntity(PathMetadata metadata) {
        super(MbtiEntity.class, metadata);
    }

}

