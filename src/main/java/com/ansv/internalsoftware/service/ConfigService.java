package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.ConfigDTO;

import java.util.List;
import java.util.Map;

public interface ConfigService {

    ConfigDTO findById(Long id);

    ConfigDTO save(ConfigDTO item);

    List<ConfigDTO> findAll();

    List<ConfigDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    ConfigDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

}
