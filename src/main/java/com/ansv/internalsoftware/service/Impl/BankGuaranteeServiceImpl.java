package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.BankGuaranteeDTO;
import com.ansv.internalsoftware.dto.response.RoleDTO;
import com.ansv.internalsoftware.model.BankGuarantee;
import com.ansv.internalsoftware.model.Role;
import com.ansv.internalsoftware.repo.BankGuaranteeRepository;
import com.ansv.internalsoftware.repo.RoleRepository;
import com.ansv.internalsoftware.service.BankGuaranteeService;
import com.ansv.internalsoftware.service.RoleService;
import com.ansv.internalsoftware.util.BaseMapper;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ansv.internalsoftware.constants.Constants.ACTIVE;


@Service
@Slf4j
public class BankGuaranteeServiceImpl implements BankGuaranteeService {

    private static final Logger logger = LoggerFactory.getLogger(BankGuaranteeServiceImpl.class);
    private static final BaseMapper<BankGuarantee, BankGuaranteeDTO> mapper = new BaseMapper<>(BankGuarantee.class, BankGuaranteeDTO.class);

    private BankGuaranteeRepository repository;


    @Override
    public BankGuaranteeDTO findById(Long id) {
        Optional<BankGuarantee> entity = repository.findById(id);
        if (entity.isPresent()) {
            BankGuaranteeDTO dto = mapper.toDtoBean(entity.get());
            return dto;
        }
        return null;
    }

    @Override
    public BankGuaranteeDTO save(BankGuaranteeDTO item) {
        BankGuarantee entity;
        if (!DataUtils.isNullOrEmpty(item.getId())) {
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
    public List<BankGuaranteeDTO> findAll() {
        List<BankGuarantee> listEntity = repository.findAll();
        List<BankGuaranteeDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public List<BankGuaranteeDTO> search(Map<String, Object> mapParam) {
        List<BankGuarantee> listEntity = repository.search(mapParam, Role.class);
        List<BankGuaranteeDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
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
