package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.request.PeriodOrderDTO;
import com.ansv.internalsoftware.dto.request.RoleDTO;
import com.ansv.internalsoftware.model.Contract;
import com.ansv.internalsoftware.model.PeriodOrder;
import com.ansv.internalsoftware.model.Role;
import com.ansv.internalsoftware.model.UserEntity;
import com.ansv.internalsoftware.repo.RoleRepository;
import com.ansv.internalsoftware.repo.UserRepository;
import com.ansv.internalsoftware.service.RoleService;
import com.ansv.internalsoftware.util.BaseMapper;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.ansv.internalsoftware.constants.Constants.ACTIVE;


@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private static final BaseMapper<Role, RoleDTO> mapper = new BaseMapper<>(Role.class, RoleDTO.class);

    private RoleRepository repository;


    @Override
    public RoleDTO findById(Long id) {
        Optional<Role> role = repository.findById(id);
        if (role.isPresent()) {
            RoleDTO dto = mapper.toDtoBean(role.get());
            return dto;
        }
        return null;
    }

    @Override
    public RoleDTO save(RoleDTO item) {
        Role entity;
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
    public List<RoleDTO> findAll() {
        return null;
    }

    @Override
    public List<RoleDTO> search(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
        List<RoleDTO> listData = repository.search(mapParam, RoleDTO.class);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
        Long count = repository.count(mapParam);
        return null;
    }

    @Override
    public RoleDTO findByCode(String code) {
        Role entity = repository.findByCode(code);
        if (!DataUtils.isNullOrEmpty(entity)) {
            RoleDTO dto = mapper.toDtoBean(entity);
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
