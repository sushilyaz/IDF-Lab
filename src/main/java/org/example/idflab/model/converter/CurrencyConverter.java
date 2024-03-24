package org.example.idflab.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Currency;

/**
 * Конвертер
 */
@Converter
public class CurrencyConverter implements AttributeConverter<Currency, String> {
    @Override
    public String convertToDatabaseColumn(Currency currency) {
        return currency.getCurrencyCode();
    }

    @Override
    public Currency convertToEntityAttribute(String s) {
        return Currency.getInstance(s);
    }
}
