package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.ProductRequest;
import com.ppsolution.ecovendas.dto.response.ProductFullResponse;
import com.ppsolution.ecovendas.dto.response.ProductResponse;
import com.ppsolution.ecovendas.exception.CategoryNotFoundException;
import com.ppsolution.ecovendas.exception.NameAlreadyInUseException;
import com.ppsolution.ecovendas.exception.ProductNotFoundException;
import com.ppsolution.ecovendas.mapper.ProductMapper;
import com.ppsolution.ecovendas.repository.CategoryRepository;
import com.ppsolution.ecovendas.repository.ProductRepository;
import com.ppsolution.ecovendas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductFullResponse createProduct(ProductRequest productRequest) {
        var category = categoryRepository.findById(productRequest.idCategory()).orElseThrow(CategoryNotFoundException::new);
        existsName(productRequest.name());
        var product = mapper.toProduct(productRequest);
        product.setCategory(category);
        product = repository.save(product);
        return mapper.toProductFullResponse(product);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long productId) {
        var product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        existsName(productRequest.name(), productId);
        var category = categoryRepository.findById(productRequest.idCategory()).orElseThrow(CategoryNotFoundException::new);
        var update = mapper.toProduct(productRequest, product);
        update.setCategory(category);
        update = repository.save(update);
        return mapper.toProductResponse(update);
    }
    

    @Override
    public ProductFullResponse getProductById(Long productId) {
        var product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return mapper.toProductFullResponse(product);
    }

    @Override
    public void enableProduct(Long productId) {
        var product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.setActive(1);
        repository.save(product);
    }

    @Override
    public void disableProduct(Long productId) {
        var product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.setActive(0);
        repository.save(product);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        var products = repository.findAll();
        return products.stream().map(mapper::toProductResponse).toList();

    }

    private void existsName(String name, Long productId) {
        if (repository.existsByNameAndIdNot(name, productId)) {
            throw new NameAlreadyInUseException();
        }
    }

    private void existsName(String name) {
        if (repository.existsByName(name)) {
            throw new NameAlreadyInUseException();
        }
    }
}
