package com.ansv.internalsoftware.controller;

import com.ansv.internalsoftware.dto.request.ContractDTO;
import com.ansv.internalsoftware.dto.request.UserDTO;
import com.ansv.internalsoftware.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/contract")
public class ContractController extends BaseController {

    @Autowired
    private ContractService contractService;

    @GetMapping("")
    public Page<ContractDTO> paging(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber ){
        // pageable begin = 0
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable pageable = pageRequest(new ArrayList<>(), pageSize, pageNumber);
        List<ContractDTO> listData = contractService.search(mapParam);
        Long totalElement = contractService.count(mapParam);
        return new PageImpl<>(listData, pageable, totalElement);

    }

    @PostMapping("")
    public ContractDTO addContract(@RequestBody  ContractDTO contract) {
        return contractService.save(contract);
    }

    @PutMapping("/{id}")
    public ContractDTO updateContract(@RequestBody ContractDTO contract, @RequestParam long id) {
        contract.setId(id);
        return contractService.save(contract);
    }

    @GetMapping("/{id}")
    public ContractDTO getById(@PathVariable(value="id") Long id) {
        return contractService.findById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable(value="id") Long id) {
        return contractService.deleteById(id);
    }

    @PostMapping("/deleteAll")
    public boolean deleteById(@RequestBody  List<Long> listId) {
        return contractService.deleteAll(listId);
    }
}
