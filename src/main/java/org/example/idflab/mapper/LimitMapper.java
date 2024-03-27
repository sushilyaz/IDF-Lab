package org.example.idflab.mapper;

import org.example.idflab.dto.NewLimitDto;
import org.example.idflab.model.Limit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Маппер для сущности Limit
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface LimitMapper {
    Limit toEntity(NewLimitDto dto);
}
