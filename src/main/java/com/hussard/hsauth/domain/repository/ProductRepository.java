package com.hussard.hsauth.domain.repository;

import com.hussard.hsauth.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
