package org.example.idflab.dto;


import lombok.Data;
import org.example.idflab.model.Category;

import java.math.BigDecimal;

@Data
public class TransactionDtoInput {
    private BigDecimal accountFrom;
    private BigDecimal accountTo;
    private Double amount;
    private String currency;
    private String category;
}
