package com.sittichot.backendledger.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sittichot.backendledger.service.TokenAuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException, TokenExpiredException {
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        verifyToken(requestTokenHeader);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void verifyToken(String requestTokenHeader) {
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            return;
        }

        String token = requestTokenHeader.substring(7);
        verifyToken(token);
        String id = tokenAuthenticationService.getUserIdFromToken(token);

        if (id == null) {
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(tokenAuthenticationService.getAuthentication(id));
    }
}