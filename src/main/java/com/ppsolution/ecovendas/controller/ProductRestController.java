package com.ppsolution.ecovendas.controller;

import com.ppsolution.ecovendas.dto.response.ProductFullResponse;
import com.ppsolution.ecovendas.dto.response.ProductResponse;
import com.ppsolution.ecovendas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        var products = service.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductFullResponse> getProductById(@PathVariable Long productId){
        var product = service.getProductById(productId);
        return ResponseEntity.ok(product);
    }
}
