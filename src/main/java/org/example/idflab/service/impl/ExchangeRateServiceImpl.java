package org.example.idflab.service.impl;

import org.example.idflab.controller.ExchangeRateClient;
import org.example.idflab.model.CurrencyMongo;
import org.example.idflab.repository.CurrencyRepository;
import org.example.idflab.service.ExchangeRateService;
import org.example.idflab.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private Parser parser;

    @Autowired
    private ExchangeRateClient exchangeRateClient;

    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Получение последнего курса валют(запрос к внешнему апи)
     * @param symbols
     * @return
     */
    @Override
    public List<CurrencyMongo> getLastCourses(List<String> symbols) {
        List<CurrencyMongo> exchangeRates = new ArrayList<>();
        for (String symbol : symbols) {
            String exchangeRate = exchangeRateClient.getExchangeRate(symbol);
            CurrencyMongo parseData = parser.parseSymbolAndClose(exchangeRate);
            exchangeRates.add(parseData);
            currencyRepository.save(parseData);
        }
        return exchangeRates;
    }

    /**
     * Получение всех валют (для проверки)
     */
    @Override
    public List<CurrencyMongo> getAllData() {
        List<CurrencyMongo> allData = currencyRepository.findAll();
        return allData;
    }

    /**
     * Получение курса запрашиваемой валюты
     * @param key
     * @return
     */
    @Override
    public BigDecimal getCurrencyByKey(String key) {
        Optional<CurrencyMongo> currencyMongo = currencyRepository.findById("USD/" + key);
        return currencyMongo.map(CurrencyMongo::getValue).orElse(null);
    }


}
