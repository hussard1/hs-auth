package com.hussard.hsauth.application.controller;

import com.hussard.hsauth.application.model.SecuredResourceResponse;
import com.hussard.hsauth.application.model.UserResponse;
import com.hussard.hsauth.application.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public List<UserResponse> admins() {
        return adminService.admins();
    }

    @GetMapping("/resources")
    public Page<SecuredResourceResponse> authorities(
        @RequestParam int page,
        @RequestParam(defaultValue = "20") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return adminService.resources(pageable);
    }

}
