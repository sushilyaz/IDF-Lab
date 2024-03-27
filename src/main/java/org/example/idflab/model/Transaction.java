package org.example.idflab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.idflab.model.converter.CurrencyConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;

/**
 * Модель транзакции
 */
@Entity
@Table(name = "transactions", schema = "idf")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "idf.transaction_id_seq", allocationSize = 1)
    @Column(name = "transaction_id")
    private Long transactionId;


    @Column(name = "account_from",nullable = false)
    private BigDecimal accountFrom;

    @Column(name = "account_to", nullable = false)
    private BigDecimal accountTo;

    // Сумма транзакции
    @Column(nullable = false)
    private BigDecimal sum; // Сумма транзакции. Для вычисления финансовых операций лучше использовать BigDecimal, т.к. он производит вычисление чисел с плавающей точкой без потери точности

    @Column(name = "currency_shortname", nullable = false)
    @Convert(converter = CurrencyConverter.class) // преобразование из String в Currency и обратно при взаимодействии с БД
    private Currency currencyShortname; // Валюта. Сначала думал сделать enum, но решил попробовать так, не знал о таком классе

    @Column(name = "expense_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category expenseCategory; // Категория

    @Column(nullable = false)
    private Timestamp datetime; // Так как приложение банковское, я считаю что здесь необходима наносекундная точность + надо учитывать timezone. К тому же timestamp напрямую поддерживается JPA

    @Column(name = "limit_exceeded", nullable = false)
    private boolean limitExceeded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "limit_id") // Название столбца в таблице транзакций, который будет хранить id лимита
    private Limit limit;
}
