package com.sittichot.backendledger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidToken extends RuntimeException{

    public InvalidToken() {
        super("Token is invalid.");
    }
}
