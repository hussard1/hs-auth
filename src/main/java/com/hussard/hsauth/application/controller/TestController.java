package com.hussard.hsauth.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    @GetMapping("/normal")
    public String test1() {
        return "normal test";
    }

    @GetMapping("/super")
    public String test2() {
        return "super test";
    }
}
