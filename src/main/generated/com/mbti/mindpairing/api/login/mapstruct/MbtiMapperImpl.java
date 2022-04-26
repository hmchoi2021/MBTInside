package com.mbti.mindpairing.api.login.mapstruct;

import com.mbti.mindpairing.api.login.dto.User.Mbti;
import com.mbti.mindpairing.api.login.entity.MbtiEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-27T02:11:51+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
public class MbtiMapperImpl implements MbtiMapper {

    @Override
    public Mbti entityToDto(MbtiEntity entity) {

        Mbti mbti = new Mbti();

        if ( entity != null ) {
            mbti.setId( entity.getId() );
            mbti.setName( entity.getName() );
        }

        return mbti;
    }

    @Override
    public List<Mbti> entitiesToDtos(List<MbtiEntity> entities) {
        if ( entities == null ) {
            return new ArrayList<Mbti>();
        }

        List<Mbti> list = new ArrayList<Mbti>( entities.size() );
        for ( MbtiEntity mbtiEntity : entities ) {
            list.add( entityToDto( mbtiEntity ) );
        }

        return list;
    }
}
