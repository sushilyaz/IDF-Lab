package org.example.idflab.repository;

import org.example.idflab.dto.AllLimitDTO;
import org.example.idflab.model.Category;
import org.example.idflab.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findTopByLimitCategoryOrderByLimitDatetimeDesc (Category category);
    @Query("SELECT new org.example.idflab.dto.AllLimitDTO(l.limitSum, l.limitCategory, l.limitDatetime, l.limitCurrencyShortname) " +
            "FROM Limit l " +
            "WHERE l.limitDatetime = ( " +
            "   SELECT MAX(limitDatetime) " +
            "   FROM Limit " +
            "   WHERE limitCategory = l.limitCategory " +
            ") " +
            "GROUP BY l.limitCategory, l.limitDatetime, l.limitSum, l.limitCurrencyShortname")
    List<AllLimitDTO> findLatestLimitsByCategory();
}
