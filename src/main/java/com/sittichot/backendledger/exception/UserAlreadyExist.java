package com.sittichot.backendledger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist(String id) {
        super("user id " + id + " is already exist.");
    }
}
