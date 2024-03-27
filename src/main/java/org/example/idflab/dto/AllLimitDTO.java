package org.example.idflab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.idflab.model.Category;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class AllLimitDTO {
    private BigDecimal limitSum;
    private Category limitCategory;
    private Timestamp limitDatetime;
    private String limitCurrencyShortname;

}
