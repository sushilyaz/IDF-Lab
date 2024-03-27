package org.example.idflab.service.impl;

import jakarta.transaction.Transactional;
import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.dto.TransactionExceededLimitDTO;
import org.example.idflab.mapper.TransactionMapper;
import org.example.idflab.model.Category;
import org.example.idflab.model.Limit;
import org.example.idflab.model.Transaction;
import org.example.idflab.repository.LimitRepository;
import org.example.idflab.repository.TransactionRepository;
import org.example.idflab.service.ExchangeRateService;
import org.example.idflab.service.LimitService;
import org.example.idflab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private LimitService limitService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Transactional
    @Override
    public void doTransaction(TransactionDtoInput dto) {
        Category currentCategory = Category.valueOf(dto.getExpenseCategory()); // try catch need
        Limit limit = limitService.getLimitByCategory(currentCategory);
        Transaction model = transactionMapper.toEntity(dto);
        model.setLimit(limit);
        model.setLimitExceeded(isLimitExceeded(dto, limit));
        transactionRepository.save(model);
    }

    @Override
    public List<TransactionExceededLimitDTO> getLimitExceededTrans() {
        List<TransactionExceededLimitDTO> asd = transactionRepository.findTransactionsExceedingLimit();
        return asd;
    }

    private boolean isLimitExceeded(TransactionDtoInput dto, Limit limit) {
        BigDecimal transactionCurrencyCourseUSD = exchangeRateService.getCurrencyByKey(dto.getCurrencyShortname());
        BigDecimal toUSD = dto.getSum().divide(transactionCurrencyCourseUSD, 2, RoundingMode.HALF_UP);
        BigDecimal substruct = limit.getBalance().subtract(toUSD);
        limit.setBalance(substruct);
        limitService.updateBalanceOfLimit(limit);
        int comparasionResult = limit.getBalance().compareTo(BigDecimal.ZERO);
        if (comparasionResult < 0) {
            return true;
        } else {
            return false;
        }
    }
}
