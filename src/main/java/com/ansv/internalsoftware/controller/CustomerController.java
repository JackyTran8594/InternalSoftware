package com.ansv.internalsoftware.controller;

import com.ansv.internalsoftware.dto.response.CustomerDTO;
import com.ansv.internalsoftware.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public Page<CustomerDTO> paging(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber ){
        // pageable begin = 0
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable pageable = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<CustomerDTO> listData = customerService.search(mapParam);
        Long totalElement = customerService.count(mapParam);
        return new PageImpl<>(listData, pageable, totalElement);

    }

    @PostMapping("")
    public CustomerDTO addContract(@RequestBody  CustomerDTO customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateContract(@RequestBody CustomerDTO customer) {
        customer.setId(customer.getId());
        return customerService.save(customer);
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable(value="id") Long id) {
        return customerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable(value="id") Long id) {
        return customerService.deleteById(id);
    }

    @PostMapping("/deleteAll")
    public boolean deleteById(@RequestBody  List<Long> listId) {
        return customerService.deleteAll(listId);
    }
}
