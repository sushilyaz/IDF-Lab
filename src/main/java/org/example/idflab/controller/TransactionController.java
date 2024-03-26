package org.example.idflab.controller;

import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.model.CurrencyMongo;
import org.example.idflab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/do-transaction")
    public ResponseEntity<List<CurrencyMongo>> doTrans(@RequestBody TransactionDtoInput dto) {
        transactionService.doTransaction(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
