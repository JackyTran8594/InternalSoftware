package com.ansv.internalsoftware.service.Impl;

import com.ansv.internalsoftware.model.Role;
import com.ansv.internalsoftware.model.UserEntity;
import com.ansv.internalsoftware.repo.RoleRepository;
import com.ansv.internalsoftware.repo.UserEntityRepository;
import com.ansv.internalsoftware.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
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
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
    private UserEntityRepository userRepository;

//    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByUsername(username);
        User newUser = null;
        if (user != null) {
            if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
                throw new UsernameNotFoundException("User not found with username: ");
            }
            List<String> role = new ArrayList<String>();
            List<Long> listRole = new ArrayList<Long>();
            List<Role> roles = roleRepository.findRoleByUserId(user.getId());
            if (DataUtils.isNullOrEmpty(roles)) {
                listRole = roles.stream().map(Role::getId).collect(Collectors.toList());
                role = roleRepository.getRoleWithList(listRole);

            }
            newUser = new User(user.getUsername(), user.getPassword(), buildSimpleGrantedAuthorities(roles, role));
        } else {
//            creating if user isn't exist in db
            log.warn("User not found with username ----> create in db", username);
            user = new UserEntity();
            user.setUsername(username);
            user.setStatus("ACTIVE");
            user.setPassword(username);
            userRepository.save(user);
            newUser =  new User(user.getUsername(), user.getPassword(), buildSimpleGrantedAuthorities(new ArrayList<>(), new ArrayList<>()));
            return newUser;
        }
        return newUser;
    }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final List<Role> roles, List<String> roleList) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        if (DataUtils.notNullOrEmpty(roleList)) {
            for (String role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }
}
