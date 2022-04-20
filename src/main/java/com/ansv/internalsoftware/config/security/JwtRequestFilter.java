package com.ansv.internalsoftware.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestToken = request.getHeader("Authorization");
        long startTime = System.currentTimeMillis();
        String reqUri = request.getRequestURI();
        String method = request.getMethod();

        if (reqUri.startsWith("/ws")) {
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
            filterChain.doFilter(request, responseWrapper);
            responseWrapper.copyBodyToResponse();
            return;
        }

        String username = null;
        String jwtToken = null;

        if (requestToken != null) {
            if (requestToken.startsWith("Bearer")) {
                jwtToken = requestToken.substring(7);
                username = jwtTokenProvider.getUsernameFromToken(jwtToken);
            } else {
                logger.warn("JWT token does not begin with Bearer string");
            }
        }

//        if ()
    }
}
