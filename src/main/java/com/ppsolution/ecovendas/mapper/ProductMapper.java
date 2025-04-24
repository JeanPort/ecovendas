package com.ppsolution.ecovendas.mapper;

import com.ppsolution.ecovendas.dto.request.ProductRequest;
import com.ppsolution.ecovendas.dto.response.ProductFullResponse;
import com.ppsolution.ecovendas.dto.response.ProductResponse;
import com.ppsolution.ecovendas.model.Product;

public interface ProductMapper {

    Product toProduct(ProductRequest productRequest);
    Product toProduct(ProductRequest productRequest, Product product);
    ProductResponse toProductResponse(Product product);
    ProductFullResponse toProductFullResponse(Product product);
}
