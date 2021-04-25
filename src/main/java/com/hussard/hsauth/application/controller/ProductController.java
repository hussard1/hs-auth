package com.hussard.hsauth.application.controller;

import com.hussard.hsauth.application.model.CommonResponse;
import com.hussard.hsauth.application.model.ProductRequest;
import com.hussard.hsauth.application.model.ProductResponse;
import com.hussard.hsauth.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public CommonResponse<Page<ProductResponse>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return CommonResponse.of(productService.list(pageable));
    }

    @PostMapping
    public CommonResponse<ProductResponse> save(@RequestBody ProductRequest productRequest) {
        return CommonResponse.of(productService.save(productRequest));
    }
}
