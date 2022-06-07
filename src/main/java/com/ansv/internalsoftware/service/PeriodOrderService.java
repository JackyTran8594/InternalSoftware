package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.request.PeriodOrderDTO;
import com.ansv.internalsoftware.dto.request.UserDTO;

import java.util.List;
import java.util.Map;

public interface PeriodOrderService {
    PeriodOrderDTO findById(Long id);

    PeriodOrderDTO save(PeriodOrderDTO id);

    List<PeriodOrderDTO> findAll();

    List<PeriodOrderDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

}
