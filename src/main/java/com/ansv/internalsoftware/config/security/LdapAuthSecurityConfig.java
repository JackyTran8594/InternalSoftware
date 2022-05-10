package com.ansv.internalsoftware.config.security;

import com.ansv.internalsoftware.config.Profiles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile(Profiles.LDAP_AUTH)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class LdapAuthSecurityConfig extends WebSecurityConfigurerAdapter  {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManager) throws Exception {
            authenticationManager.inMemoryAuthentication().withUser("adminansv").password(passwordEncoder().encode("admin@123"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword.toString());
            }
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}
