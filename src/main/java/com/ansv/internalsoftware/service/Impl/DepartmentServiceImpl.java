package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.DepartmentDTO;
import com.ansv.internalsoftware.model.Department;
import com.ansv.internalsoftware.repo.DepartmentRepository;
import com.ansv.internalsoftware.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private static final BaseMapper<Department, DepartmentDTO> mapper = new BaseMapper<>(Department.class, DepartmentDTO.class);

    private DepartmentRepository repository;

    @Override
    public DepartmentDTO findById(Long id) {
        Optional<Department> entity = repository.findById(id);
        if(entity.isPresent()) {
            DepartmentDTO dto = mapper.toDtoBean(entity.get());
            return dto;
        }
        return null;
    }

    @Override
    public DepartmentDTO save(DepartmentDTO item) {
        Department entity;
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
    public List<DepartmentDTO> findAll() {
        List<Department> listEntity = repository.findAll();
        List<DepartmentDTO> listDTO = mapper.toDtoBean(listEntity);
        return listDTO;
    }

    @Override
    public List<DepartmentDTO> search(Map<String, Object> mapParam) {
        List<Department> listEntity = repository.search(mapParam, Department.class);
        List<DepartmentDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
    }

    @Override
    public DepartmentDTO findByCode(String code) {
        Department entity = repository.findByCode(code);
        if (!DataUtils.isNullOrEmpty(entity)) {
            DepartmentDTO dto = mapper.toDtoBean(entity);
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

    @Override
    public List<DepartmentDTO> findDepartmentByUserId(Long id) {
        try {
            List<Department> listEntity = repository.findDepartmentByUserId(id);
            List<DepartmentDTO> listData = mapper.toDtoBean(listEntity);
            return listData;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
