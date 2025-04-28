package com.ppsolution.ecovendas.service;

import com.ppsolution.ecovendas.dto.request.ProductRequest;
import com.ppsolution.ecovendas.dto.response.ProductFullResponse;
import com.ppsolution.ecovendas.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductFullResponse createProduct(ProductRequest productRequest);
    ProductFullResponse updateProduct(ProductRequest productRequest, Long productId);
    ProductFullResponse getProductById(Long productId);
    void enableProduct(Long productId);
    void disableProduct(Long productId);
    List<ProductResponse> getAllProduct();
}
