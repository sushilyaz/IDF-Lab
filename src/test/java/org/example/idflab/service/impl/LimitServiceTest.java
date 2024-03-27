package org.example.idflab.service.impl;

import org.example.idflab.mapper.LimitMapper;
import org.example.idflab.model.Category;
import org.example.idflab.model.Limit;
import org.example.idflab.repository.LimitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LimitServiceTest {

    @Mock
    private LimitRepository limitRepository;

    @InjectMocks
    private LimitServiceImpl limitService;

    @Test
    void testGetLimitByCategory_ExistingLimit() {
        Category category = Category.SERVICES;
        Limit limit = new Limit();
        limit.setBalance(BigDecimal.TEN);
        limit.setLimitSum(BigDecimal.valueOf(100));
        when(limitRepository.findTopByLimitCategoryOrderByLimitDatetimeDesc(category)).thenReturn(Optional.of(limit));

        Limit result = limitService.getLimitByCategory(category);

        assertEquals(BigDecimal.TEN, result.getBalance());
    }

    @Test
    void testGetLimitByCategory_DefaultLimit() {
        Category category = Category.SERVICES;
        when(limitRepository.findTopByLimitCategoryOrderByLimitDatetimeDesc(category)).thenReturn(Optional.empty());

        Limit result = limitService.getLimitByCategory(category);

        assertEquals(BigDecimal.valueOf(1000), result.getBalance());
    }

    @Test
    void testUpdateBalanceOfLimit() {
        Limit limit = new Limit();

        limitService.updateBalanceOfLimit(limit);

        verify(limitRepository).saveAndFlush(limit);
    }

}
