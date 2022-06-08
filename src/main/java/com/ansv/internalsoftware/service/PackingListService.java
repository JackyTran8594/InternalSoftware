package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.FunctionDTO;
import com.ansv.internalsoftware.dto.response.PackingListDTO;

import java.util.List;
import java.util.Map;

public interface PackingListService {
    PackingListDTO findById(Long id);

    PackingListDTO save(PackingListDTO item);

    List<PackingListDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);


}
