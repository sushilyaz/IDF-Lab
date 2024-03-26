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
    Transaction toEntity(TransactionDtoInput dto);

//    default BigDecimal doubleToBigDecimal(Double amount) {
//        return BigDecimal.valueOf(amount);
//    }
//
//    default Currency stringToCurrency(String currencyCode) {
//        return Currency.getInstance(currencyCode);
//    }
//    default Category stringToCategory(String categoryCode) {
//        return Category.valueOf(categoryCode);
//    }
}
