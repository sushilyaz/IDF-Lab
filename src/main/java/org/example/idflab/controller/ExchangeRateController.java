package org.example.idflab.controller;

import org.example.idflab.model.CurrencyMongo;
import org.example.idflab.service.ExchangeRateService;
import org.example.idflab.util.CurrencyProperties;
import org.example.idflab.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CurrencyProperties currencyProperties;

    /**
     * Апи для получения курса (обращение к внешнему АПИ)
     * @return
     */
    @GetMapping("/api/get-last-course")
    public ResponseEntity<List<CurrencyMongo>> getLastCourse() {
        List<String> symbols = currencyProperties.getCurrencies();
        List<CurrencyMongo> resultList = exchangeRateService.getLastCourses(symbols);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultList);
    }

    /**
     * Для проверки
     * @return
     */
    @GetMapping("/get-course")
    public ResponseEntity<List<CurrencyMongo>> getCourse() {
        List<CurrencyMongo> resultList = exchangeRateService.getAllData();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultList);
    }
}
