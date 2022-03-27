package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportEntity is a Querydsl query type for ReportEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportEntity extends EntityPathBase<ReportEntity> {

    private static final long serialVersionUID = -1873961510L;

    public static final QReportEntity reportEntity = new QReportEntity("reportEntity");

    public final NumberPath<Long> anoUserId = createNumber("anoUserId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath doYn = createString("doYn");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath reason = createString("reason");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QReportEntity(String variable) {
        super(ReportEntity.class, forVariable(variable));
    }

    public QReportEntity(Path<? extends ReportEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportEntity(PathMetadata metadata) {
        super(ReportEntity.class, metadata);
    }

}

