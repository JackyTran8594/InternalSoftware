package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.UserDTO;
import com.ansv.internalsoftware.model.UserEntity;
import com.ansv.internalsoftware.repo.UserEntityRepository;
import com.ansv.internalsoftware.service.UserService;
import com.ansv.internalsoftware.mapper.BaseMapper;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ansv.internalsoftware.constants.Constants.ACTIVE;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private static final BaseMapper<UserEntity, UserDTO> mapper = new BaseMapper<>(UserEntity.class, UserDTO.class);
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserEntityRepository userRepository;

    @Override
    public UserDTO findById(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if(entity.isPresent()) {
            UserDTO dto = mapper.toDtoBean(entity.get());
            return dto;
        }
        return null;
    }

    @Override
    public UserDTO save(UserDTO user) {
        UserEntity entity;
        if(!DataUtils.isNullOrEmpty(user.getId())) {
            user.setLastModifiedDate(LocalDateTime.now());
            entity = mapper.toPersistenceBean(user);
        } else {
            entity = mapper.toPersistenceBean(user);
            entity.setStatus(ACTIVE);
        }
        return mapper.toDtoBean(userRepository.save(entity));

    }

    @Override
    public List<UserDTO> findAll() {
        List<UserEntity> listEntity = userRepository.findAll();
        List<UserDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public List<UserDTO> search(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
        List<UserEntity> listEntity = userRepository.search(mapParam, UserEntity.class);
        List<UserDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = userRepository.count(mapParam);
        return count;
    }

    @Override
    public UserDTO findByUsername(String username) {

        UserEntity user = userRepository.findByUsername(username);
        if(user != null) {
            UserDTO dto = mapper.toDtoBean(user);
            return dto;
        }
        return null;

    }

    @Override
    public List<UserDTO> findByDepartmentid(Long departmentId) {
        List<UserEntity> ListData = userRepository.findUserByDepartmentId(departmentId);
        return mapper.toDtoBean(ListData);
    }

    @Override
    public List<UserDTO> findByCode(String code) {
        try {
            List<UserEntity> listEntity = userRepository.findByCode(code);
            List<UserDTO> listData = mapper.toDtoBean(listEntity);
            return listData;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }



    @Override
    public Boolean asyncUserLDAP() {
        return null;
    }
}
