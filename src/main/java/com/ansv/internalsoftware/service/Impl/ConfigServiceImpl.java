package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.ConfigDTO;
import com.ansv.internalsoftware.dto.response.CustomerDTO;
import com.ansv.internalsoftware.model.Config;
import com.ansv.internalsoftware.model.Customer;
import com.ansv.internalsoftware.model.Department;
import com.ansv.internalsoftware.repo.ConfigRepository;
import com.ansv.internalsoftware.repo.CustomerRepository;
import com.ansv.internalsoftware.service.ConfigService;
import com.ansv.internalsoftware.service.CustomerService;
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
public class ConfigServiceImpl implements ConfigService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);
    private static final BaseMapper<Config, ConfigDTO> mapper = new BaseMapper<>(Config.class, ConfigDTO.class);

    private ConfigRepository repository;


    @Override
    public ConfigDTO findById(Long id) {
        Optional<Config> role = repository.findById(id);
        if (role.isPresent()) {
            ConfigDTO dto = mapper.toDtoBean(role.get());
            return dto;
        }
        return null;
    }

    @Override
    public ConfigDTO save(ConfigDTO item) {
        Config entity;
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
    public List<ConfigDTO> findAll() {
        List<Config> listEntity = repository.findAll();
        List<ConfigDTO> listDTO = mapper.toDtoBean(listEntity);
        return listDTO;
    }

    @Override
    public List<ConfigDTO> search(Map<String, Object> mapParam) {
        List<Config> listEntity = repository.search(mapParam, Config.class);
        List<ConfigDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
    }

    @Override
    public ConfigDTO findByCode(String code) {
        Config entity = repository.findByCode(code);
        if (!DataUtils.isNullOrEmpty(entity)) {
            ConfigDTO dto = mapper.toDtoBean(entity);
            return dto;
        }
        return null;
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
