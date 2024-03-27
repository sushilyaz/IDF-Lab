package org.example.idflab.controller;

import org.example.idflab.dto.NewLimitDto;
import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.model.CurrencyMongo;
import org.example.idflab.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LimitController {

    @Autowired
    private LimitService limitService;

    @PostMapping("/create-new-limit")
    public ResponseEntity<List<CurrencyMongo>> createNewLimit(@RequestBody NewLimitDto dto) {
        limitService.setNewLimit(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
