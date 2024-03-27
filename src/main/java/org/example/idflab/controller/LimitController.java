package org.example.idflab.controller;

import org.example.idflab.dto.AllLimitDTO;
import org.example.idflab.dto.NewLimitDto;
import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.model.CurrencyMongo;
import org.example.idflab.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/limit-action")
public class LimitController {

    @Autowired
    private LimitService limitService;

    @PostMapping("/create-new-limit")
    public ResponseEntity<List<CurrencyMongo>> createNewLimit(@RequestBody NewLimitDto dto) {
        limitService.setNewLimit(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/get-all-current-limits")
    public ResponseEntity<List<AllLimitDTO>> getAllLimit() {
        List<AllLimitDTO> res = limitService.getCurrentLimit();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(res);
    }
}
