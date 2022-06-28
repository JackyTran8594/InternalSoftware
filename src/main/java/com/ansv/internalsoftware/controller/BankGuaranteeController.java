package com.ansv.internalsoftware.controller;


import com.ansv.internalsoftware.dto.response.BankGuaranteeDTO;
import com.ansv.internalsoftware.service.BankGuaranteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bankguarantee")
public class BankGuaranteeController extends BaseController {

    @Autowired
    private BankGuaranteeService service;

    @GetMapping("")
    public Page<BankGuaranteeDTO> pageable(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<BankGuaranteeDTO> listData = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(listData,page,totalElement);
    }

    @PostMapping("")
    public BankGuaranteeDTO add(@RequestBody BankGuaranteeDTO item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public BankGuaranteeDTO update(@RequestBody BankGuaranteeDTO item) {
        item.setId(item.getId());
        return service.save(item);
    }

    @GetMapping("/{id}")
    public BankGuaranteeDTO getById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable(value="id") Long id) {
        return service.deleteById(id);
    }

    @PostMapping("/deleteAll")
    public boolean deleteById(@RequestBody  List<Long> listId) {
        return service.deleteAll(listId);
    }

}
