package org.example.idflab.dto;

import lombok.Data;
import org.example.idflab.model.Category;

import java.math.BigDecimal;
@Data
public class NewLimitDto {
    private BigDecimal amount;
    private Category category;
}
