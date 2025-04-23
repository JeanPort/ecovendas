package com.ppsolution.ecovendas.service;

import com.ppsolution.ecovendas.dto.request.ProductRequest;
import com.ppsolution.ecovendas.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(ProductRequest productRequest, Long productId);
    ProductResponse getProductById(Long productId);
    ProductResponse enableProduct(Long productId);
    List<ProductResponse> getAllProduct();
}
