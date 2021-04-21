package com.hussard.hsauth.application.controller;

import com.hussard.hsauth.application.model.CommonResponse;
import com.hussard.hsauth.application.model.UserRequest;
import com.hussard.hsauth.application.model.UserResponse;
import com.hussard.hsauth.application.service.UserService;
import com.hussard.hsauth.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public CommonResponse<UserResponse> get(@Min(1) @PathVariable long id) {
        return CommonResponse.of(userService.getUser(id));
    }
}
