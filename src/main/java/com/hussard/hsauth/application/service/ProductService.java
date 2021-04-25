package com.hussard.hsauth.application.service;

import com.hussard.hsauth.application.model.ProductRequest;
import com.hussard.hsauth.application.model.ProductResponse;
import com.hussard.hsauth.domain.service.ProductDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDomainService productDomainService;

    public Page<ProductResponse> list(Pageable pageable) {
        return productDomainService.getList(pageable).map(ProductResponse::of);
    }

    public ProductResponse save(ProductRequest productRequest) {
        return ProductResponse.of(productDomainService.save(productRequest.toEntity()));
    }
}
