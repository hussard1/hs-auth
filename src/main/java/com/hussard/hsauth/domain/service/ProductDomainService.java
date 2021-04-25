package com.hussard.hsauth.domain.service;

import com.hussard.hsauth.domain.entity.Product;
import com.hussard.hsauth.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDomainService {

    private final ProductRepository productRepository;

    public Page<Product> getList(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
