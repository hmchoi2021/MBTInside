package com.mbti.mindpairing.api.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTermsAgreementEntity is a Querydsl query type for TermsAgreementEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTermsAgreementEntity extends EntityPathBase<TermsAgreementEntity> {

    private static final long serialVersionUID = -1512563031L;

    public static final QTermsAgreementEntity termsAgreementEntity = new QTermsAgreementEntity("termsAgreementEntity");

    public final DateTimePath<java.time.LocalDateTime> agreement_time = createDateTime("agreement_time", java.time.LocalDateTime.class);

    public final StringPath agreement_yn = createString("agreement_yn");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> term_id = createNumber("term_id", Long.class);

    public final NumberPath<Long> user_id = createNumber("user_id", Long.class);

    public QTermsAgreementEntity(String variable) {
        super(TermsAgreementEntity.class, forVariable(variable));
    }

    public QTermsAgreementEntity(Path<? extends TermsAgreementEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTermsAgreementEntity(PathMetadata metadata) {
        super(TermsAgreementEntity.class, metadata);
    }

}

