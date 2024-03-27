package org.example.idflab.repository;

import org.example.idflab.dto.TransactionExceededLimitDTO;
import org.example.idflab.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    /**
     * SQL запрос на получение транзакций с флагом true и соответствуюшим им лимитам
     * @return
     */
    @Query("SELECT new org.example.idflab.dto.TransactionExceededLimitDTO(t.accountFrom, t.accountTo, t.sum, t.currencyShortname, t.expenseCategory, t.datetime, l.limitSum, l.limitDatetime, l.limitCurrencyShortname) " +
            "FROM Transaction t " +
            "JOIN Limit l ON t.limit.limitId = l.limitId " +
            "WHERE t.limitExceeded = true")
    List<TransactionExceededLimitDTO> findTransactionsExceedingLimit();
}
