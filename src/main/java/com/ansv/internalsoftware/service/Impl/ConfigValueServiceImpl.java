package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.ConfigValueDTO;
import com.ansv.internalsoftware.model.ConfigValue;
import com.ansv.internalsoftware.repo.ConfigValueRepository;
import com.ansv.internalsoftware.service.ConfigValueService;
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
public class ConfigValueServiceImpl implements ConfigValueService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigValueServiceImpl.class);
    private static final BaseMapper<ConfigValue, ConfigValueDTO> mapper = new BaseMapper<>(ConfigValue.class, ConfigValueDTO.class);

    private ConfigValueRepository repository;


    @Override
    public ConfigValueDTO findById(Long id) {
        Optional<ConfigValue> role = repository.findById(id);
        if (role.isPresent()) {
            ConfigValueDTO dto = mapper.toDtoBean(role.get());
            return dto;
        }
        return null;
    }

    @Override
    public ConfigValueDTO save(ConfigValueDTO item) {
        ConfigValue entity;
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
    public List<ConfigValueDTO> findAll() {
        List<ConfigValue> listEntity = repository.findAll();
        List<ConfigValueDTO> listDTO = mapper.toDtoBean(listEntity);
        return listDTO;
    }

//    @Override
//    public List<ConfigValueDTO> search(Map<String, Object> mapParam) {
//        List<ConfigValue> listEntity = repository.search(mapParam, ConfigValue.class);
//        List<ConfigValueDTO> listData = mapper.toDtoBean(listEntity);
//        return listData;
//    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
    }


//    @Override
//    public ConfigValueDTO findByCode(String code) {
//        ConfigValue entity = repository.findByCode(code);
//        if (!DataUtils.isNullOrEmpty(entity)) {
//            ConfigValueDTO dto = mapper.toDtoBean(entity);
//            return dto;
//        }
//        return null;
//    }

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
