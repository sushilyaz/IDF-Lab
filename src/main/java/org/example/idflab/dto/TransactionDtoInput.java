package org.example.idflab.dto;


import lombok.Data;
import org.example.idflab.model.Category;

@Data
public class TransactionDtoInput {
    private Double amount;
    private String currency;
    private Category category;
}
