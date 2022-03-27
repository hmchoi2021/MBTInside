package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTermsEntity is a Querydsl query type for TermsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTermsEntity extends EntityPathBase<TermsEntity> {

    private static final long serialVersionUID = -229676985L;

    public static final QTermsEntity termsEntity = new QTermsEntity("termsEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mandatoryYn = createString("mandatoryYn");

    public final StringPath startTime = createString("startTime");

    public final EnumPath<com.mbti.mindpairing.api.login.dto.User.TermsStatus> status = createEnum("status", com.mbti.mindpairing.api.login.dto.User.TermsStatus.class);

    public final StringPath termsInfo = createString("termsInfo");

    public final StringPath title = createString("title");

    public QTermsEntity(String variable) {
        super(TermsEntity.class, forVariable(variable));
    }

    public QTermsEntity(Path<? extends TermsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTermsEntity(PathMetadata metadata) {
        super(TermsEntity.class, metadata);
    }

}

