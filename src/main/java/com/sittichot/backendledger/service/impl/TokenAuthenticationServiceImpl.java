package com.sittichot.backendledger.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sittichot.backendledger.entity.User;
import com.sittichot.backendledger.service.TokenAuthenticationService;
import com.sittichot.backendledger.util.ClockUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {
    @Value("${jwt.expire-time}")
    private int EXPIRATION_TIME; // 30 seconds timeout
    private final Algorithm ALGORITHM;

    public TokenAuthenticationServiceImpl(@Value("${jwt.secret-key}") String secretKey) {
        ALGORITHM = Algorithm.HMAC256(secretKey);
    }

    @Override
    public String createJWT(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(createExpire())
                .sign(ALGORITHM);
    }

    @Override
    public Authentication getAuthentication(String username) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        return authenticationToken;
    }

    @Override
    public String getUserIdFromToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (TokenExpiredException er) {
            log.info("token={} is expire", token);
            return null;
        }
    }

    @Override
    public String getToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (TokenExpiredException er) {
            log.info("token={} is expire", token);
            return null;
        }
    }

    private Date createExpire() {
        return ClockUtils.createDateFutureByField(Calendar.MINUTE, EXPIRATION_TIME);
    }
}
