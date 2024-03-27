package org.example.idflab.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.idflab.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO согласно ТЗ. В APi микросервиса транзакция заходит в таком формате. Только преобразовал названия переменных из snake_case в lowerCamelCase
 */
@Data
@AllArgsConstructor
public class TransactionDtoInput {
    private Long accountFrom;
    private Long accountTo;
    private BigDecimal sum;
    private String currencyShortname;
    private String expenseCategory;
    private Timestamp datetime;
}
