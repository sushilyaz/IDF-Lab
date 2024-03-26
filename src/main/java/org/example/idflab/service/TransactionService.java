package org.example.idflab.service;

import org.example.idflab.dto.TransactionDtoInput;

public interface TransactionService {
    void doTransaction(TransactionDtoInput dto);
}
