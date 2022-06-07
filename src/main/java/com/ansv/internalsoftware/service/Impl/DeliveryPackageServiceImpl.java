package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.DeliveryPackageDTO;
import com.ansv.internalsoftware.dto.response.DepartmentDTO;
import com.ansv.internalsoftware.dto.response.PeriodOrderDTO;
import com.ansv.internalsoftware.model.Contract;
import com.ansv.internalsoftware.model.DeliveryPackage;
import com.ansv.internalsoftware.model.Department;
import com.ansv.internalsoftware.model.PeriodOrder;
import com.ansv.internalsoftware.repo.DeliveryPackageRepository;
import com.ansv.internalsoftware.repo.PeriodOrderRepository;
import com.ansv.internalsoftware.service.DeliveryPackageService;
import com.ansv.internalsoftware.service.PeriodOrderService;
import com.ansv.internalsoftware.util.BaseMapper;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ansv.internalsoftware.constants.Constants.ACTIVE;


@Service
@Slf4j
public class DeliveryPackageServiceImpl implements DeliveryPackageService {
    public static final Logger logger = LoggerFactory.getLogger(DeliveryPackageServiceImpl.class);

    public static final BaseMapper<DeliveryPackage, DeliveryPackageDTO> mapper = new BaseMapper<>(DeliveryPackage.class, DeliveryPackageDTO.class);

    private DeliveryPackageRepository repository;

    @Override
    public DeliveryPackageDTO findById(Long id) {
        Optional<DeliveryPackage> dp = repository.findById(id);
        if(dp.isPresent()) {
            DeliveryPackageDTO dto = mapper.toDtoBean(dp.get());
            return dto;
        }
        return null;
    }

    @Override
    public DeliveryPackageDTO save(DeliveryPackageDTO item) {
        DeliveryPackage entity;
        if(!DataUtils.isNullOrEmpty(item.getId())) {
            item.setLastModifiedDate(LocalDateTime.now());
            entity = mapper.toPersistenceBean(item);
        } else {
            entity = mapper.toPersistenceBean(item);
            entity.setStatus(ACTIVE);
        }
        entity = repository.save(entity);
        return mapper.toDtoBean(entity);
    }

    @Override
    public List<DeliveryPackageDTO> findAll() {
        List<DeliveryPackage> listEntity = repository.findAll();
        List<DeliveryPackageDTO> listDTO = mapper.toDtoBean(listEntity);
        return listDTO;
    }

    @Override
    public List<DeliveryPackageDTO> search(Map<String, Object> mapParam) {
        List<DeliveryPackage> listEntity = repository.search(mapParam, DeliveryPackage.class);
        List<DeliveryPackageDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAll(List<Long> listId) {
        try {
            Integer delete = repository.deleteAll(listId);
            if(delete > 0) {
                return true;
            }
            return false;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
