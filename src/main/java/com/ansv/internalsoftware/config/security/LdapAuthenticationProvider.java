package com.ansv.internalsoftware.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LdapAuthenticationProvider implements AuthenticationProvider {

    private Environment environment;

//    @Value("${spring.ldap.server.url}")
    private String url = "ldap://172.24.104.6:389";
    private String userDn = "CN=Users,DC=ansv,DC=vn";
    private String base = "DC=ansv,DC=vn";


//    @Value("${spring.ldap.server.base}")

    private LdapContextSource contextSource;
    private LdapTemplate ldapTemplate;

    private void initContext()
    {
        contextSource = new LdapContextSource();
        contextSource.setUrl(url);
        contextSource.setAnonymousReadOnly(true);
//        contextSource.setUserDn(userDn);
//        contextSource.setBase(base);
        contextSource.afterPropertiesSet();

        ldapTemplate = new LdapTemplate(contextSource);
    }

    public LdapAuthenticationProvider(Environment environment) {
            this.environment = environment;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        initContext();
        Filter filter = new EqualsFilter("userPrincipalName", authentication.getPrincipal().toString());
        Boolean authenticate = ldapTemplate.authenticate(LdapUtils.newLdapName(userDn), filter.encode(), authentication.getCredentials().toString());
        if (authenticate)
        {
            UserDetails userDetails = new User(authentication.getName(), authentication.getCredentials().toString()
                    , new ArrayList<>());
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                    authentication.getCredentials().toString(), new ArrayList<>());
            return auth;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
