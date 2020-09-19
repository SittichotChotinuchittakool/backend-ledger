package com.sittichot.backendledger.entity;

import lombok.Data;

@Data
public class Token {
    private String token;

    public static Token create(String jwt) {
        Token token = new Token();
        token.setToken(jwt);

        return token;
    }
}
