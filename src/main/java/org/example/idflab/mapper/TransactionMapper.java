package org.example.idflab.mapper;

import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.model.Category;
import org.example.idflab.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.util.Currency;


/**
 * Маппер для сущности Transaction
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface TransactionMapper {
    Transaction toEntity(TransactionDtoInput dto);
}
