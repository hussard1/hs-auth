package com.hussard.hsauth.application.model;

import com.hussard.hsauth.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class ProductRequest {

    @NotBlank
    private final String name;
    @NotBlank
    private final String displayName;

    public Product toEntity() {
        return Product
                .builder()
                .name(name)
                .displayName(displayName)
                .build();
    }
}
