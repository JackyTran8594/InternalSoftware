package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.FunctionDTO;
import com.ansv.internalsoftware.dto.response.RoleDTO;

import java.util.List;
import java.util.Map;

public interface FunctionService {
    FunctionDTO findById(Long id);

    FunctionDTO save(FunctionDTO item);

    List<FunctionDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);


}
