package com.hussard.hsauth.application.controller;

import com.hussard.hsauth.application.model.AuthResponse;
import com.hussard.hsauth.application.model.CommonResponse;
import com.hussard.hsauth.application.model.UserRequest;
import com.hussard.hsauth.application.model.UserResponse;
import com.hussard.hsauth.application.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public CommonResponse<List<UserResponse>> list() {
        return CommonResponse.of(adminService.list());
    }

}
