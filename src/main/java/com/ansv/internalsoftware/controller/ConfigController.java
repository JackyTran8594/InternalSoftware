package com.ansv.internalsoftware.controller;


import com.ansv.internalsoftware.dto.response.ConfigDTO;
import com.ansv.internalsoftware.dto.response.DeliveryPackageDTO;
import com.ansv.internalsoftware.service.ConfigService;
import com.ansv.internalsoftware.service.DeliveryPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigService service;


    @GetMapping("")
    public Page<ConfigDTO> pageable(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageSize, pageNumber);
        List<ConfigDTO> listData = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(listData,page,totalElement);
    }

    @PostMapping("")
    public ConfigDTO addConfig(@RequestBody ConfigDTO item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public ConfigDTO updateConfig(@RequestBody ConfigDTO item,@RequestParam long id) {
        item.setId(id);
        return service.save(item);
    }

    @GetMapping("/{id}")
    public ConfigDTO getById(@PathVariable(value="id") Long id) {
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
