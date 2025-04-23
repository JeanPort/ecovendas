package com.ppsolution.ecovendas.service;

import com.ppsolution.ecovendas.dto.request.CategoryRequest;
import com.ppsolution.ecovendas.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCatory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long idCategory, CategoryRequest categoryRequest);
    void disableCatory(Long idCategory);
    List<CategoryResponse> getAllCatorys();
    CategoryResponse getCatoryById(Long id);

}
