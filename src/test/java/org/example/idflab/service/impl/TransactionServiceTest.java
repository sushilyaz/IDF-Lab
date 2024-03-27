package org.example.idflab.service.impl;

import jakarta.transaction.Transactional;
import org.example.idflab.IdfLabApplicationTests;
import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.mapper.TransactionMapper;
import org.example.idflab.model.Limit;
import org.example.idflab.model.Transaction;
import org.example.idflab.repository.TransactionRepository;
import org.example.idflab.service.ExchangeRateService;
import org.example.idflab.service.LimitService;
import org.example.idflab.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест флага с использованием testContainer postgres
 */
@Transactional
class TransactionServiceTest extends IdfLabApplicationTests {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("Test doTransaction with not limit exceeded")
    public void testDoTransaction_LimitNotExceeded() {
        // Ставим 1000 рублей, лимит 1000 баксов установится автоматически (лимит по умолчанию)
        TransactionDtoInput dto = new TransactionDtoInput(1L, 2L, new BigDecimal(1000), "RUB", "SERVICES", new Timestamp(System.currentTimeMillis()));

        transactionService.doTransaction(dto);

        List<Transaction> transactions = transactionRepository.findAll();
        assertFalse(transactions.isEmpty());
        Transaction savedTransaction = transactions.get(0);
        assertFalse(savedTransaction.isLimitExceeded());
    }

    @Test
    @DisplayName("Test doTransaction with limit exceeded")
    public void testDoTransaction_LimitExceeded() {
        // Ставим 1000000 рублей, лимит 1000 баксов установится автоматически (лимит по умолчанию)
        TransactionDtoInput dto = new TransactionDtoInput(1L, 2L, new BigDecimal(1000000), "RUB", "SERVICES", new Timestamp(System.currentTimeMillis()));

        transactionService.doTransaction(dto);

        List<Transaction> transactions = transactionRepository.findAll();
        assertFalse(transactions.isEmpty());
        Transaction savedTransaction = transactions.get(0);
        assertTrue(savedTransaction.isLimitExceeded());
    }
}


