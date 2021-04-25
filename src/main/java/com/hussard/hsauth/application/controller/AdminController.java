package com.hussard.hsauth.application.controller;

import com.hussard.hsauth.application.model.CommonResponse;
import com.hussard.hsauth.application.model.UserResponse;
import com.hussard.hsauth.application.service.AdminService;
import com.hussard.hsauth.application.model.SecuredResourceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public CommonResponse<List<UserResponse>> admins() {
        return CommonResponse.of(adminService.admins());
    }

    @GetMapping("/resources")
    public CommonResponse<Page<SecuredResourceResponse>> authorities(
        @RequestParam int page,
        @RequestParam(defaultValue = "20") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return CommonResponse.of(adminService.resources(pageable));
    }

}
