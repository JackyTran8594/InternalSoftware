package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.ConfigDTO;
import com.ansv.internalsoftware.dto.response.ConfigValueDTO;

import java.util.List;
import java.util.Map;

public interface ConfigValueService {

    ConfigValueDTO findById(Long id);

    ConfigValueDTO save(ConfigValueDTO item);

    List<ConfigValueDTO> findAll();

//    List<ConfigValueDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

//    ConfigValueDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

}
