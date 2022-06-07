package com.ansv.internalsoftware.service;

import com.ansv.internalsoftware.dto.response.CustomerDTO;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    CustomerDTO findById(Long id);

    CustomerDTO save(CustomerDTO item);

    List<CustomerDTO> findAll();

    List<CustomerDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    CustomerDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

}
