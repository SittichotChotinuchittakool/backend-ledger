package com.sittichot.backendledger.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import com.sittichot.backendledger.exception.InvalidToken;
import com.sittichot.backendledger.service.TokenAuthenticationService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthenticatedAuthenticationToken token = (PreAuthenticatedAuthenticationToken) authentication;
        String jwtToken = (String) token.getCredentials();
        String username = tokenAuthenticationService.getUserIdFromToken(jwtToken);

        if (username == null) {
            throw new InvalidToken();
        }

        PreAuthenticatedAuthenticationToken authenticatedToken
                = new PreAuthenticatedAuthenticationToken(username, null, Collections.emptyList());
        authenticatedToken.setDetails(token.getDetails());
        authenticatedToken.setAuthenticated(true);

        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
