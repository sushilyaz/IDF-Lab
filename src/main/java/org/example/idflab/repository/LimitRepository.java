package org.example.idflab.repository;

import org.example.idflab.model.Category;
import org.example.idflab.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findTopByCategoryOrderByLimitDateDesc(Category category);
}
