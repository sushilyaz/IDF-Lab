package org.example.idflab.dto;


import lombok.Data;
import org.example.idflab.model.Category;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TransactionDtoInput {
    private Long accountFrom;
    private Long accountTo;
    private BigDecimal sum;
    private String currencyShortname;
    private String expenseCategory;
    private Timestamp datetime;
}
