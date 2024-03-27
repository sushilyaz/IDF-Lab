package org.example.idflab.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.idflab.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
