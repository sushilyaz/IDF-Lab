package org.example.idflab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Модель Limit
 * Поле Currency отсутствует, т.к. лимит всегда устанавливается в USD
 */
@Entity
@Table(name = "limits", schema = "idf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "limit_id_seq")
    @SequenceGenerator(name = "limit_id_seq", sequenceName = "idf.limit_id_seq", allocationSize = 1)
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
    @CreationTimestamp
    private Timestamp limitDate; // Дата установления лимита
}



