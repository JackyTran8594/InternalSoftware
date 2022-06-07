package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.request.ContractDTO;

import java.util.List;
import java.util.Map;

public interface ContractService {

    ContractDTO findById(Long id);

    ContractDTO save(ContractDTO id);

    List<ContractDTO> findAll();

    List<ContractDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    Boolean deleteAll(List<Long> listid);

    Boolean deleteById(Long Id);

}
