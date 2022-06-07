package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.DepartmentDTO;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    DepartmentDTO findById(Long id);

    DepartmentDTO save(DepartmentDTO item);

    List<DepartmentDTO> findAll();

    List<DepartmentDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    DepartmentDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

    List<DepartmentDTO> findDepartmentByUserId(Long id);
}
