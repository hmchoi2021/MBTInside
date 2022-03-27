package com.mbti.mindpairing.api.login.mapstruct;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.MbtiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface MbtiMapper {
    public static MbtiMapper INSTANCE = Mappers.getMapper(MbtiMapper.class);
    User.Mbti entityToDto(MbtiEntity entity);
    List<User.Mbti> entitiesToDtos(List<MbtiEntity> entities);
}
