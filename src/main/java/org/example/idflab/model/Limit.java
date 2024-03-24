package org.example.idflab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Модель Limit
 * Поле Currency отсутствует, т.к. лимит всегда устанавливается в USD
 */
@Entity
@Table(name = "limits", schema = "idf")
@Data
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "limit_id")
    private Long limitId;

    @Column(nullable = false)
    private BigDecimal balance; // Остаток лимита. Буду вычитать из него значение произведенной транзакции. При обновлении лимита буду прибавлять

    @Column(nullable = false)
    private BigDecimal amount; /* Общий лимит. Допустим, пользователь хочет посмотреть свой общий лимит, поле необходимо для этого.
    Или в дальнейшем необходимо будет в зависимости от "карты" устанавливать разные лимиты. Это поле может пригодиться */

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category; // Категория для лимита (по ТЗ необходимо иметь раздельные лимит для категорий)

    @Column(name = "limit_date", nullable = false)
    private Timestamp limitDate; // Дата установления лимита
}
