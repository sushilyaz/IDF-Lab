package org.example.idflab.service;

import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.dto.TransactionExceededLimitDTO;

import java.util.List;

public interface TransactionService {
    void doTransaction(TransactionDtoInput dto);
    List<TransactionExceededLimitDTO> getLimitExceededTrans();
}
