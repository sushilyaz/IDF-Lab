package org.example.idflab.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Обращение к внешнему API для запроса курса валют. Какие валюты запрашивать можно указать в application.yml файле.
 * Я сделал так специально, чтобы можно было обновить всего лишь 1 файлик и не заморачиваться в коде
 */
@FeignClient(name = "ExchangeRateClient", url = "${spring.cloud.openfeign.client.config.default.url}")
public interface ExchangeRateClient {

    @GetMapping("/time_series?apikey=3cb41c7cf7084a829b742b12eb1f1706&interval=1day&format=JSON&outputsize=1")
    String getExchangeRate(@RequestParam("symbol") String symbol);
}
