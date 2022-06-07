package com.ansv.internalsoftware.controller;


import com.ansv.internalsoftware.dto.request.ContractDTO;
import com.ansv.internalsoftware.dto.request.PeriodOrderDTO;
import com.ansv.internalsoftware.service.PeriodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/periodOrder")
public class PeriodOrderController extends BaseController {

    @Autowired
    private PeriodOrderService service;


    @GetMapping("")
    public Page<PeriodOrderDTO> pageable(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageSize, pageNumber);
        List<PeriodOrderDTO> listData = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(listData,page,totalElement);
    }

    @PostMapping("")
    public PeriodOrderDTO addPeriodOrder(@RequestBody PeriodOrderDTO item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public PeriodOrderDTO updatePeriodOrder(@RequestBody PeriodOrderDTO item,@RequestParam long id) {
        item.setId(id);
        return service.save(item);
    }

    @GetMapping("/{id}")
    public PeriodOrderDTO getById(@PathVariable(value="id") Long id) {
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
