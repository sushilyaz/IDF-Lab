package org.example.idflab.mapper;

import org.example.idflab.dto.NewLimitDto;
import org.example.idflab.model.Limit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface LimitMapper {

    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "category", target = "category")
    Limit toEntity(NewLimitDto dto);
}
