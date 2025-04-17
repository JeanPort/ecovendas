package com.ppsolution.ecovendas.mapper.impl;

import com.ppsolution.ecovendas.dto.request.CategoryRequest;
import com.ppsolution.ecovendas.dto.response.CategoryResponse;
import com.ppsolution.ecovendas.mapper.CategoryMapper;
import com.ppsolution.ecovendas.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {


    @Override
    public Category toCategory(CategoryRequest categoryRequest) {
        if (categoryRequest == null) return null;
        return Category.builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .active(1)
                .build();
    }

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        if (category == null) return null;
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getActive(),
                category.getCreatedAt(),
                category.getUpdatedAt());
    }
}
