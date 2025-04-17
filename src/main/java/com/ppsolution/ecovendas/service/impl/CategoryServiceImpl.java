package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.CategoryRequest;
import com.ppsolution.ecovendas.dto.response.CategoryResponse;
import com.ppsolution.ecovendas.mapper.CategoryMapper;
import com.ppsolution.ecovendas.model.Category;
import com.ppsolution.ecovendas.repository.CategoryRepository;
import com.ppsolution.ecovendas.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponse createCatory(CategoryRequest categoryRequest) {
        var category = mapper.toCategory(categoryRequest);
        category = categoryRepository.save(category);
        return mapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Long idCategory, CategoryRequest categoryRequest) {

        return null;
    }

    @Override
    public CategoryResponse disableCatory(Long idCategory) {
        return null;
    }

    @Override
    public List<CategoryResponse> getAllCatorys() {
        return List.of();
    }

    @Override
    public CategoryResponse getCatoryById(Long id) {
        return null;
    }
}
