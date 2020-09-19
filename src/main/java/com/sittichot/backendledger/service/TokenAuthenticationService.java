package com.sittichot.backendledger.service;

import org.springframework.security.core.Authentication;

import com.sittichot.backendledger.entity.User;

public interface TokenAuthenticationService {
    String createJWT(User user);
    Authentication getAuthentication(String username);
    String getUserIdFromToken(String token);
    String getToken(String token);
}
