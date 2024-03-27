package org.example.idflab.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.idflab.model.Category;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;

/**
 * DTO для получения транзакций с превышенным лимитом, согласно ТЗ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionExceededLimitDTO {
    private BigDecimal accountFrom;
    private BigDecimal accountTo;
    private BigDecimal sum;
    private Currency currencyShortname;
    private Category expenseCategory;
    private Timestamp datetime;
    private BigDecimal limitSum;
    private Timestamp limitDatetime;
    private String limitCurrencyShortname;
}
