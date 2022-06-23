package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.dto.response.CustomerDTO;
import com.ansv.internalsoftware.model.Customer;
import com.ansv.internalsoftware.repo.CustomerRepository;
import com.ansv.internalsoftware.service.CustomerService;
import com.ansv.internalsoftware.util.BaseMapper;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ansv.internalsoftware.constants.Constants.ACTIVE;


@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private static final BaseMapper<Customer, CustomerDTO> mapper = new BaseMapper<>(Customer.class, CustomerDTO.class);

    @Autowired
    private CustomerRepository repository;


    @Override
    public CustomerDTO findById(Long id) {
        Optional<Customer> role = repository.findById(id);
        if (role.isPresent()) {
            CustomerDTO dto = mapper.toDtoBean(role.get());
            return dto;
        }
        return null;
    }

    @Override
    public CustomerDTO save(CustomerDTO item) {
        Customer entity;
        if(!DataUtils.isNullOrEmpty(item.getId())) {
            item.setLastModifiedDate(LocalDateTime.now());
            entity = mapper.toPersistenceBean(item);
        } else {
            entity = mapper.toPersistenceBean(item);
            entity.setStatus(ACTIVE);
        }
//        entity =
        return mapper.toDtoBean(repository.save(entity));
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> listEntity = repository.findAll();
        List<CustomerDTO> listDTO = mapper.toDtoBean(listEntity);
        return listDTO;
    }

    @Override
    public List<CustomerDTO> search(Map<String, Object> mapParam) {
        List<Customer> listEntity = repository.search(mapParam, Customer.class);
        List<CustomerDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
    }

    @Override
    public CustomerDTO findByCode(String code) {
        Customer entity = repository.findByCode(code);
        if (!DataUtils.isNullOrEmpty(entity)) {
            CustomerDTO dto = mapper.toDtoBean(entity);
            return dto;
        }
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAll(List<Long> listId) {
        try {
            Integer delete = repository.deleteAll(listId);
            if(delete > 0) {
                return true;
            }
            return false;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

}
