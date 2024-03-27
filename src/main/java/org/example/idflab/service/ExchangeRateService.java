package org.example.idflab.service;

import org.example.idflab.model.CurrencyMongo;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateService {
    List<CurrencyMongo> getLastCourses(List<String> symbols);
    List<CurrencyMongo> getAllData();

    BigDecimal getCurrencyByKey(String key);
}
