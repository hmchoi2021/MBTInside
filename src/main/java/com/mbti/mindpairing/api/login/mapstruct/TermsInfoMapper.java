package com.mbti.mindpairing.api.login.mapstruct;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.TermsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface TermsInfoMapper {
    public static TermsInfoMapper INSTANCE = Mappers.getMapper(TermsInfoMapper.class);
//    @Mapping(target = "", source = "")
    User.TermsInfoResponse entityToDto(TermsEntity entity);
    List<User.TermsInfoResponse> entitiesToDtos(List<TermsEntity> entities);
}
