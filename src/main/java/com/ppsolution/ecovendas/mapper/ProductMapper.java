package com.ppsolution.ecovendas.mapper;

import com.ppsolution.ecovendas.dto.request.ProductRequest;
import com.ppsolution.ecovendas.dto.response.ProductResponse;
import com.ppsolution.ecovendas.model.Product;

public interface ProductMapper {

    Product toProduct(ProductRequest productRequest);
    ProductResponse toProductResponse(Product product);
}
