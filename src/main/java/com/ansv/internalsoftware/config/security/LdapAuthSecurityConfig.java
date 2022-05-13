// package com.ansv.internalsoftware.config.security;

// import com.ansv.internalsoftware.config.Profiles;
// import lombok.RequiredArgsConstructor;
// import net.minidev.json.writer.BeansMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.BeanIds;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import javax.servlet.http.HttpServletResponse;

// // @Profile(Profiles.LDAP_AUTH)
// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
// @RequiredArgsConstructor
// public class LdapAuthSecurityConfig extends WebSecurityConfigurerAdapter {

//    private JwtRequestFilter jwtRequestFilter;

//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//     //    authenticationManagerBuilder.inMemoryAuthentication().withUser("adminansv").password(passwordEncoder().encode("admin@123"));
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
// //        enable cors and disable csrf
//        http.cors().and().csrf().disable();

// //        set session management to stateless
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

//        // set unauthorized requests exception handler
//        http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//        }).and();

//        // set permission on endpoints
//        http.authorizeRequests()
//                // public endpoints
// //                .antMatchers("/").permitAll()
//                .antMatchers("/api/auth/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()


//                // private endpoints
//                .antMatchers("/api/user/**").permitAll()

//                .anyRequest().authenticated();

//        // add jwt token filter
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }

//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.equals(rawPassword.toString());
//            }
//        };
//    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
// }
