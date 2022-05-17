package com.ansv.internalsoftware.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler  {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFailureHandler.class);


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub

        try {
            System.out.println(response.getOutputStream());
            System.out.println(response.getContentType());
            // System.out.println(response.getHeaders(arg0));
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
    }

   
    
}
