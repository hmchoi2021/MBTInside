package com.mbti.mindpairing.api.login.mapstruct;

import com.mbti.mindpairing.api.login.dto.User.TermsInfoResponse;
import com.mbti.mindpairing.api.login.entity.TermsEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-23T14:28:34+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
public class TermsInfoMapperImpl implements TermsInfoMapper {

    @Override
    public TermsInfoResponse entityToDto(TermsEntity entity) {

        TermsInfoResponse termsInfoResponse = new TermsInfoResponse();

        if ( entity != null ) {
            termsInfoResponse.setId( entity.getId() );
            termsInfoResponse.setTitle( entity.getTitle() );
            termsInfoResponse.setTermsInfo( entity.getTermsInfo() );
            termsInfoResponse.setStartTime( entity.getStartTime() );
            termsInfoResponse.setMandatoryYn( entity.getMandatoryYn() );
            if ( entity.getStatus() != null ) {
                termsInfoResponse.setStatus( entity.getStatus().name() );
            }
        }

        return termsInfoResponse;
    }

    @Override
    public List<TermsInfoResponse> entitiesToDtos(List<TermsEntity> entities) {
        if ( entities == null ) {
            return new ArrayList<TermsInfoResponse>();
        }

        List<TermsInfoResponse> list = new ArrayList<TermsInfoResponse>( entities.size() );
        for ( TermsEntity termsEntity : entities ) {
            list.add( entityToDto( termsEntity ) );
        }

        return list;
    }
}
