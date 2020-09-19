package com.sittichot.backendledger.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sittichot.backendledger.entity.LoginForm;
import com.sittichot.backendledger.entity.Token;
import com.sittichot.backendledger.entity.User;
import com.sittichot.backendledger.exception.UserAlreadyExist;
import com.sittichot.backendledger.repository.UserRepository;
import com.sittichot.backendledger.service.TokenAuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/authen")
public class AuthenticationController {
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login")
    public Token login(@RequestBody @Valid LoginForm req) {
        Optional<User> optionalUser = userRepository.findByUserIdAndPassword(req.getUserId(), req.getPassword());

        if (optionalUser.isEmpty()) {
            throw new UserAlreadyExist(req.getUserId());
        }

        return Token.create(tokenAuthenticationService.createJWT(optionalUser.get()));
    }

    @PostMapping(value = "/registration")
    public User registration(@RequestBody User user) {
        Optional<User> optionalUser = userRepository.findByUserId(user.getUserId());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExist(user.getUserId());
        }

        return userRepository.save(user);
    }
}
