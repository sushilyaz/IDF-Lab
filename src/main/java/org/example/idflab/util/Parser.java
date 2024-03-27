package org.example.idflab.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.idflab.exception.ParseExternalApiDataException;
import org.example.idflab.model.CurrencyMongo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class Parser {
    public CurrencyMongo parseSymbolAndClose(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;
        try {
           node = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new ParseExternalApiDataException("Cant parse data from external API");
        }
        String symbol = node.get("meta").get("symbol").asText();
        BigDecimal close = new BigDecimal(node.get("values").get(0).get("close").asText());
        return new CurrencyMongo(symbol, close);
    }
}
