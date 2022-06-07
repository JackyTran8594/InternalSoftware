package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.PeriodOrderDTO;

import java.util.List;
import java.util.Map;

public interface PeriodOrderService {
    PeriodOrderDTO findById(Long id);

    PeriodOrderDTO save(PeriodOrderDTO item);

    List<PeriodOrderDTO> findAll();

    List<PeriodOrderDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

}
