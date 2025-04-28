package com.ppsolution.ecovendas.mapper.impl;

import com.ppsolution.ecovendas.dto.request.ProductRequest;
import com.ppsolution.ecovendas.dto.response.ProductFullResponse;
import com.ppsolution.ecovendas.dto.response.ProductResponse;
import com.ppsolution.ecovendas.mapper.CategoryMapper;
import com.ppsolution.ecovendas.mapper.ProductMapper;
import com.ppsolution.ecovendas.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {

    private final CategoryMapper categoryMapper;


    @Override
    public Product toProduct(ProductRequest productRequest) {
        if (productRequest == null) return null;
        return Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .active(1)
                .description(productRequest.description())
                .stockQuantity(productRequest.stockQuantity())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public Product toProduct(ProductRequest productRequest, Product product) {
        if (productRequest == null) return null;
        return Product.builder()
                .id(product.getId())
                .name(productRequest.name())
                .price(productRequest.price())
                .active(product.getActive())
                .description(productRequest.description())
                .stockQuantity(productRequest.stockQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public ProductResponse toProductResponse(Product product) {
        if (product == null) return null;
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getUrlImage(),
                product.getActive(),
                product.getCreatedAt(),
                product.getUpdatedAt());
    }

    @Override
    public ProductFullResponse toProductFullResponse(Product product) {
        if (product == null) return null;
        var categoryResponse = categoryMapper.toCategoryResponse(product.getCategory());
        return new ProductFullResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getUrlImage(),
                product.getActive(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                categoryResponse);
    }
}
