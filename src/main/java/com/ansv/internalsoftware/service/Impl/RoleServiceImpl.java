package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.model.Role;
import com.ansv.internalsoftware.model.UserEntity;
import com.ansv.internalsoftware.repo.RoleRepository;
import com.ansv.internalsoftware.repo.UserRepository;
import com.ansv.internalsoftware.service.RoleService;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


}
