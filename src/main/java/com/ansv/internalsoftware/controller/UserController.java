package com.ansv.internalsoftware.controller;

import com.ansv.internalsoftware.dto.request.UserDTO;
import com.ansv.internalsoftware.service.UserService;
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
@RequestMapping("api/users")
public class UserController extends BaseController {


    @Autowired
    private UserService userService;


    @GetMapping("")
    public Page<UserDTO> paging(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        // pageable begin 0
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageNumber", pageNumber);
        mapParam.put("pageSize", pageSize);
        Pageable pageable = pageRequest(new ArrayList<>(), pageSize, pageNumber);
        List<UserDTO> listData = userService.search(mapParam);
        Long totalElement = userService.count(mapParam);
        return new PageImpl<>(listData, pageable, totalElement);
    }

    @PutMapping("")
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable(value="id") Long id) {
        return userService.findById(id);
    }

//    @DeleteMapping("/{id}")
//    public boolean deleteById(@PathVariable(value="id") Long id) {
//        return userService.deleteById(id);
//    }

    @PostMapping("/async")
    public Boolean asyncUserLDAP() {

        return true;
    }
}
