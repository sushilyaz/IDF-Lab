package org.example.idflab.service;

import org.example.idflab.dto.NewLimitDto;
import org.example.idflab.model.Category;
import org.example.idflab.model.Limit;

public interface LimitService {
    Limit getLimitByCategory(Category category);

    void updateBalanceOfLimit(Limit limit);

    void setNewLimit(NewLimitDto dto);
}
