package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDTO findById(Long id);

    UserDTO save(UserDTO item);

    List<UserDTO> findAll();

    List<UserDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    UserDTO findByUsername(String username);

    List<UserDTO> findByDepartmentid(Long departmentId);

    List<UserDTO> findByCode(String code);

    Boolean asyncUserLDAP();
}
