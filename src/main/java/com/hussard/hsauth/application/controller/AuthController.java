package com.hussard.hsauth.application.controller;

import com.hussard.hsauth.application.model.AuthResponse;
import com.hussard.hsauth.application.model.UserRequest;
import com.hussard.hsauth.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/signin")
    public AuthResponse signin(@RequestBody UserRequest userRequest) {
        return authService.signin(userRequest);
    }

    @PostMapping(value = "/signup")
    public AuthResponse signup(@Validated @RequestBody UserRequest userRequest) {
        return authService.signup(userRequest);
    }
}
