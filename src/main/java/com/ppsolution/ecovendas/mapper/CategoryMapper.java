package com.ppsolution.ecovendas.mapper;

import com.ppsolution.ecovendas.dto.request.CategoryRequest;
import com.ppsolution.ecovendas.dto.response.CategoryResponse;
import com.ppsolution.ecovendas.model.Category;

public interface CategoryMapper {

    Category toCategory(CategoryRequest categoryRequest);
    CategoryResponse toCategoryResponse(Category category);

}
