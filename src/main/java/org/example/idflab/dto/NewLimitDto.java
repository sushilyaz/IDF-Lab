package org.example.idflab.dto;

import lombok.Data;
import org.example.idflab.model.Category;

import java.math.BigDecimal;

/**
 * Установка нового лимита
 */
@Data
public class NewLimitDto {
    private BigDecimal limitSum;
    private String limitCategory;
    private String limitCurrencyShortname;
}
