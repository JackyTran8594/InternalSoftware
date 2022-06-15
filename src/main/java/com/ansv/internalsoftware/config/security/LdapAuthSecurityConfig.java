package com.ansv.internalsoftware.config.security;

import com.ansv.internalsoftware.config.Profiles;
import lombok.RequiredArgsConstructor;
import net.minidev.json.writer.BeansMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ansv.internalsoftware.config.LdapUserAuthoritiesPopulator;

import javax.servlet.http.HttpServletResponse;

@Profile({Profiles.LDAP_AUTH_DEV, Profiles.LDAP_AUTH_STAGING})
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class LdapAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LdapAuthSecurityConfig.class);
    private final JwtRequestFilter jwtRequestFilter;
    private final LdapUserAuthoritiesPopulator ldapUserAuthoritiesPopulator;
    private Environment env;


    @Override
//    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        try {
            authenticationManagerBuilder.inMemoryAuthentication().withUser("adminansv").password(passwordEncoder().encode("admin@123")).roles("ADMIN");
//            authenticationManagerBuilder.authenticationProvider(new LdapAuthenticationProvider(env)).eraseCredentials(false);
            authenticationManagerBuilder
                    .ldapAuthentication()
                    // Pass the LDAP patterns for finding the username.
//				// The key "{0}" will be substituted with the username
                    .userDnPatterns("CN=users,DC=ansv,DC=vn")
                    .userSearchBase("CN=users,DC=ansv,DC=vn")
                    .userSearchFilter("(userPrincipalName={0})")
                    // Pass search base as argument for group membership searches.
//                    .groupSearchBase("ou=users")
                    .contextSource().url("ldap://172.24.104.6")
                    .port(389)
//                    // DN of the user who will bind to the LDAP server to perform the search
                    .managerDn("CN=Admin,CN=Users,DC=ansv,DC=vn")
                    // Password of the user who will bind to the LDAP server to perform the search
                    .managerPassword("123456")
                    .and()

//                    .userSearchBase("cn=users")
                    // Configures LDAP compare operation of the user password to authenticate
//                    .passwordCompare()
//                    .passwordEncoder(new BCryptPasswordEncoder())
////
//                    // Specifies the attribute in the directory which contains the user password.
//                    // Defaults to "userPassword".
//                    .passwordAttribute("userPassword")
//                    .and()
                    .ldapAuthoritiesPopulator(ldapUserAuthoritiesPopulator);

        } catch (AuthenticationException e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        enable cors and disable csrf
        http.cors().and().csrf().disable();

        //        set session management to stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        // set unauthorized requests exception handler
        http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }).and();

        // set permission on endpoints
        http.authorizeRequests()
                // public endpoints
                //                .antMatchers("/").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()


                // private endpoints
                .antMatchers("/api/user/**").permitAll()

                .anyRequest().authenticated();

        // add jwt token filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

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
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
