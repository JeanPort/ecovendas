package com.ppsolution.ecovendas.controller.admin;

import com.ppsolution.ecovendas.dto.request.ProductRequest;
import com.ppsolution.ecovendas.dto.response.ProductFullResponse;
import com.ppsolution.ecovendas.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AdminProductRestController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductFullResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        var product = service.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductFullResponse> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductRequest productRequest){
        var product = service.updateProduct(productRequest, productId);
        return ResponseEntity.ok(product);
    }
}
