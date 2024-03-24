package org.example.idflab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.idflab.model.converter.CurrencyConverter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;

@Entity
@Table(name = "transactions", schema = "idf")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    // Сумма транзакции
    @Column(nullable = false)
    private BigDecimal amount; // Сумма транзакции. Для вычисления финансовых операций лучше использовать BigDecimal, т.к. он производит вычисление чисел с плавающей точкой без потери точности

    @Column(nullable = false)
    @Convert(converter = CurrencyConverter.class) // преобразование из String в Currency и обратно при взаимодействии с БД
    private Currency currency; // Валюта. Сначала думал сделать enum, но решил попробовать так, не знал о таком классе

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category; // Категория

    @Column(name = "transaction_date", nullable = false)
    private Timestamp transactionDate; // Так как приложение банковское, я считаю что здесь необходима наносекундная точность + надо учитывать timezone. К тому же timestamp напрямую поддерживается JPA

    @Column(name = "limit_exceeded", nullable = false)
    private boolean limitExceeded;
}
