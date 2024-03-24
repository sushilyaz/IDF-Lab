package org.example.idflab.service;

import org.example.idflab.model.CurrencyMongo;

import java.util.List;
import java.util.Map;

public interface ExchangeRateService {
    List<CurrencyMongo> getLastCourses(List<String> symbols);
    List<CurrencyMongo> getAllData();
}
