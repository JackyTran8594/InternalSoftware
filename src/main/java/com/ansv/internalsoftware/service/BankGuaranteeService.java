package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.BankGuaranteeDTO;
import com.ansv.internalsoftware.dto.response.RoleDTO;

import java.util.List;
import java.util.Map;

public interface BankGuaranteeService {
    BankGuaranteeDTO findById(Long id);

    BankGuaranteeDTO save(BankGuaranteeDTO item);

    List<BankGuaranteeDTO> findAll();

    List<BankGuaranteeDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);


    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);


}
