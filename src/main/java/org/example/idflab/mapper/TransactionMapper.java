package org.example.idflab.mapper;

import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.model.Category;
import org.example.idflab.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.util.Currency;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "accountFrom", target = "accountFrom")
    @Mapping(source = "accountTo", target = "accountTo")
    Transaction toEntity(TransactionDtoInput dto);
}
