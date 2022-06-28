package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.ContractDTO;
import com.ansv.internalsoftware.model.Contract;
import com.ansv.internalsoftware.repo.ContractRepository;
import com.ansv.internalsoftware.service.ContractService;
import com.ansv.internalsoftware.mapper.BaseMapper;
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
public class ContractServiceImpl implements ContractService {

    private static final BaseMapper<Contract, ContractDTO> mapper = new BaseMapper<>(Contract.class, ContractDTO.class);
    private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

    private ContractRepository repository;

    @Override
    public ContractDTO findById(Long id) {
        Optional<Contract> contract = repository.findById(id);
        if(contract.isPresent()) {
            ContractDTO dto = mapper.toDtoBean(contract.get());
            return dto;
        }
        return null;
    }

    @Override
    public ContractDTO save(ContractDTO item) {
        Contract entity;
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
    public List<ContractDTO> findAll() {
        List<Contract> listEntity = repository.findAll();
        List<ContractDTO> listDTO = mapper.toDtoBean(listEntity);
        return listDTO;
    }

    @Override
    public List<ContractDTO> search(Map<String, Object> mapParam) {
        List<Contract> listEntity = repository.search(mapParam, Contract.class);
        List<ContractDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
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
}
