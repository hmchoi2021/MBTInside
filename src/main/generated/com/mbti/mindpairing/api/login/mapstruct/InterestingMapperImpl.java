package com.mbti.mindpairing.api.login.mapstruct;

import com.mbti.mindpairing.api.login.dto.User.Interesting;
import com.mbti.mindpairing.api.login.entity.InterestingEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-27T16:01:27+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
public class InterestingMapperImpl implements InterestingMapper {

    @Override
    public Interesting entityToDto(InterestingEntity entity) {

        Interesting interesting = new Interesting();

        if ( entity != null ) {
            interesting.setId( entity.getId() );
            interesting.setName( entity.getName() );
        }

        return interesting;
    }

    @Override
    public List<Interesting> entitiesToDtos(List<InterestingEntity> entities) {
        if ( entities == null ) {
            return new ArrayList<Interesting>();
        }

        List<Interesting> list = new ArrayList<Interesting>( entities.size() );
        for ( InterestingEntity interestingEntity : entities ) {
            list.add( entityToDto( interestingEntity ) );
        }

        return list;
    }
}
