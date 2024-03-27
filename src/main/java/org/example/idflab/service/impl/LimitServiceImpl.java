package org.example.idflab.service.impl;

import org.example.idflab.dto.AllLimitDTO;
import org.example.idflab.dto.NewLimitDto;
import org.example.idflab.mapper.LimitMapper;
import org.example.idflab.model.Category;
import org.example.idflab.model.Limit;
import org.example.idflab.repository.LimitRepository;
import org.example.idflab.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class LimitServiceImpl implements LimitService {

    @Autowired
    private LimitRepository limitRepository;

    @Autowired
    private LimitMapper limitMapper;

    @Override
    public Limit getLimitByCategory(Category category) {
        Optional<Limit> limit = limitRepository.findTopByLimitCategoryOrderByLimitDatetimeDesc(category);
        return limit.orElseGet(() -> setDefaultLimit(category));
    }

    @Override
    public void updateBalanceOfLimit(Limit limit) {
        limitRepository.saveAndFlush(limit);
    }

    @Override
    public void setNewLimit(NewLimitDto dto) {
        Optional<Limit> lastLimit = limitRepository.findTopByLimitCategoryOrderByLimitDatetimeDesc(Category.valueOf(dto.getLimitCategory()));
        Limit model = limitMapper.toEntity(dto);
        if (lastLimit.isPresent()) {
            BigDecimal newBalance = lastLimit.get().getBalance().add(dto.getLimitSum()).subtract(lastLimit.get().getLimitSum());
            model.setBalance(newBalance);
        } else {
            model.setBalance(dto.getLimitSum());
        }
        limitRepository.save(model);
    }

    @Override
    public List<AllLimitDTO> getCurrentLimit() {
        return limitRepository.findLatestLimitsByCategory();
    }

    private Limit setDefaultLimit(Category category) {
        Limit defaultLimit = Limit.builder()
                .balance(BigDecimal.valueOf(1000))
                .limitSum(new BigDecimal("1000"))
                .limitCategory(category)
                .limitCurrencyShortname("USD")
                .build();
        limitRepository.save(defaultLimit);
        return defaultLimit;
    }
}
