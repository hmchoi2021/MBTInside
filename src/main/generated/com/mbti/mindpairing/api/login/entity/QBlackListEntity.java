package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlackListEntity is a Querydsl query type for BlackListEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlackListEntity extends EntityPathBase<BlackListEntity> {

    private static final long serialVersionUID = 1463047421L;

    public static final QBlackListEntity blackListEntity = new QBlackListEntity("blackListEntity");

    public final DateTimePath<java.time.LocalDateTime> dueDate = createDateTime("dueDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public final StringPath status = createString("status");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBlackListEntity(String variable) {
        super(BlackListEntity.class, forVariable(variable));
    }

    public QBlackListEntity(Path<? extends BlackListEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlackListEntity(PathMetadata metadata) {
        super(BlackListEntity.class, metadata);
    }

}

