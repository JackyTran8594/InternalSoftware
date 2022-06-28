package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.PeriodOrderDTO;
import com.ansv.internalsoftware.model.PeriodOrder;
import com.ansv.internalsoftware.repo.PeriodOrderRepository;
import com.ansv.internalsoftware.service.PeriodOrderService;
import com.ansv.internalsoftware.mapper.BaseMapper;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ansv.internalsoftware.constants.Constants.ACTIVE;


@Service
@Slf4j
public class PeriodOrderServiceImpl implements PeriodOrderService {
    public static final Logger logger = LoggerFactory.getLogger(PeriodOrderServiceImpl.class);

    public static final BaseMapper<PeriodOrder, PeriodOrderDTO> mapper = new BaseMapper<>(PeriodOrder.class, PeriodOrderDTO.class);
    @Autowired
    private PeriodOrderRepository repository;

    @Override
    public PeriodOrderDTO findById(Long id) {
        Optional<PeriodOrder> po = repository.findById(id);
        if(po.isPresent()) {
            PeriodOrderDTO dto = mapper.toDtoBean(po.get());
            return dto;
        }
        return null;
    }

    @Override
    public PeriodOrderDTO save(PeriodOrderDTO item) {
        PeriodOrder entity;
        if(!DataUtils.isNullOrEmpty(item.getId())) {
            item.setLastModifiedDate(LocalDateTime.now());
            entity = mapper.toPersistenceBean(item);
        } else {
            entity = mapper.toPersistenceBean(item);
            entity.setStatus(ACTIVE);
        }
        return mapper.toDtoBean(repository.save(entity));

    }

    @Override
    public List<PeriodOrderDTO> findAll() {
        List<PeriodOrder> listEntity = repository.findAll();
        List<PeriodOrderDTO> listDTO = mapper.toDtoBean(listEntity);
        return listDTO;
    }

    @Override
    public List<PeriodOrderDTO> search(Map<String, Object> mapParam) {
        List<PeriodOrder> listEntity = repository.search(mapParam, PeriodOrder.class);
        List<PeriodOrderDTO> listData = mapper.toDtoBean(listEntity);
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
