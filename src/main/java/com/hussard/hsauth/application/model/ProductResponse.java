package com.hussard.hsauth.application.model;

import com.hussard.hsauth.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

    private final long id;
    private final String name;
    private final String displayName;

    public static ProductResponse of(Product product) {
        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .displayName(product.getDisplayName())
                .build();
    }
}
