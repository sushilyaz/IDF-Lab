package org.example.idflab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "currency")
@Data
@AllArgsConstructor
public class CurrencyMongo {

    @Id
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private BigDecimal value;
}
