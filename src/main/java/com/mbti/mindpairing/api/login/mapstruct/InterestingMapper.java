package com.mbti.mindpairing.api.login.mapstruct;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.InterestingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface InterestingMapper {
    public static InterestingMapper INSTANCE = Mappers.getMapper(InterestingMapper.class);
    User.Interesting entityToDto(InterestingEntity entity);
    List<User.Interesting> entitiesToDtos(List<InterestingEntity> entities);
}
