package org.example.idflab.controller;

import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.dto.TransactionExceededLimitDTO;
import org.example.idflab.model.CurrencyMongo;
import org.example.idflab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-action")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/do-transaction")
    public ResponseEntity<String> doTrans(@RequestBody TransactionDtoInput dto) {
        transactionService.doTransaction(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/get-trans-limit-exceeded")
    public ResponseEntity<List<TransactionExceededLimitDTO>> getLimitExceeded() {
        List<TransactionExceededLimitDTO> res = transactionService.getLimitExceededTrans();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }
}
