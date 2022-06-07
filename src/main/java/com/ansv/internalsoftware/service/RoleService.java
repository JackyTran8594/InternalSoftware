package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.request.RoleDTO;

import java.util.List;
import java.util.Map;

public interface RoleService {
    RoleDTO findById(Long id);

    RoleDTO save(RoleDTO id);

    List<RoleDTO> findAll();

    List<RoleDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    RoleDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

}
