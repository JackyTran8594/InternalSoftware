package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.DeliveryPackageDTO;
import com.ansv.internalsoftware.dto.response.PeriodOrderDTO;

import java.util.List;
import java.util.Map;

public interface DeliveryPackageService {
    DeliveryPackageDTO findById(Long id);

    DeliveryPackageDTO save(DeliveryPackageDTO item);

    List<DeliveryPackageDTO> findAll();

    List<DeliveryPackageDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

}
