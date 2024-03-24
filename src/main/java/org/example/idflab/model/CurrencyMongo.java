package org.example.idflab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "currency")
@Data
public class CurrencyMongo {
//    @Id
//    private Long currency_id;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private Double value;

    public CurrencyMongo(String currency, Double value) {
        this.currency = currency;
        this.value = value;
    }
}
