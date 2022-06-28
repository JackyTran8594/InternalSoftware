package com.ansv.internalsoftware.controller;


import com.ansv.internalsoftware.dto.response.FunctionDTO;
import com.ansv.internalsoftware.dto.response.PackingListDTO;
import com.ansv.internalsoftware.service.FunctionService;
import com.ansv.internalsoftware.service.PackingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/packingList")
public class PackingListController extends BaseController {

    @Autowired
    private PackingListService service;

    @GetMapping("")
    public Page<PackingListDTO> pageable(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<PackingListDTO> listData = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(listData,page,totalElement);
    }

    @PostMapping("")
    public PackingListDTO add(@RequestBody PackingListDTO item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public PackingListDTO update(@RequestBody PackingListDTO item,@RequestParam long id) {
        item.setId(id);
        return service.save(item);
    }

    @GetMapping("/{id}")
    public PackingListDTO getById(@PathVariable(value="id") Long id) {
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
