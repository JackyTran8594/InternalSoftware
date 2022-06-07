package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.request.UserDTO;
import com.ansv.internalsoftware.model.UserEntity;
import com.ansv.internalsoftware.repo.UserRepository;
import com.ansv.internalsoftware.service.UserService;
import com.ansv.internalsoftware.util.BaseMapper;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.ansv.internalsoftware.constants.Constants.ACTIVE;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private static final BaseMapper<UserEntity, UserDTO> mapper = new BaseMapper<>(UserEntity.class, UserDTO.class);

    private UserRepository userRepository;

    @Override
    public UserDTO findById(Long id) {
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
        entity = userRepository.save(entity);
        return  mapper.toDtoBean(entity);
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public List<UserDTO> search(Map<String, Object> mapParam) {
        List<UserDTO> ListData = userRepository.search(mapParam, UserEntity.class);
        return ListData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = userRepository.count(mapParam);
        return count;
    }

    @Override
    public UserDTO findByUsername(String username) {
        UserEntity user = userRepository.findUserByUsername(username);
        return mapper.toDtoBean(user);
    }

    @Override
    public List<UserDTO> findByDepartmentid(Long departmentId) {
        List<UserEntity> ListData = userRepository.findUserByDepartmentId(departmentId);
        return mapper.toDtoBean(ListData);
    }

    @Override
    public List<UserDTO> findByCode(String departmentCode) {
        return null;
    }

    @Override
    public Boolean deleteById(List<Long> listid) {
        return null;
    }

    @Override
    public Boolean asyncUserLDAP() {
        return null;
    }
}
