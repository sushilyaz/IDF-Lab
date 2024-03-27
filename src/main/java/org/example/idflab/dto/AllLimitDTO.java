package org.example.idflab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.idflab.model.Category;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO для получения действующих лимитов всех категорий
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllLimitDTO {
    private BigDecimal limitSum;
    private Category limitCategory;
    private Timestamp limitDatetime;
    private String limitCurrencyShortname;

}
