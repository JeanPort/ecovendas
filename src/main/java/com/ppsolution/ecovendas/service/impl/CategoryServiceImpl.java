package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.CategoryRequest;
import com.ppsolution.ecovendas.dto.response.CategoryResponse;
import com.ppsolution.ecovendas.exception.CategoryNotFoundException;
import com.ppsolution.ecovendas.exception.NameAlreadyInUseException;
import com.ppsolution.ecovendas.mapper.CategoryMapper;
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
        existName(categoryRequest.name());
        var category = mapper.toCategory(categoryRequest);
        category = categoryRepository.save(category);
        return mapper.toCategoryResponse(category);
    }



    @Override
    public CategoryResponse updateCategory(Long idCategory, CategoryRequest categoryRequest) {
        categoryRepository.findById(idCategory).orElseThrow(CategoryNotFoundException::new);
        var category = mapper.toCategory(categoryRequest);
        category.setId(idCategory);
        existName(category.getName(), category.getId());
        category = categoryRepository.save(category);
        return mapper.toCategoryResponse(category);
    }



    @Override
    public CategoryResponse disableCatory(Long idCategory) {
        var category = categoryRepository.findById(idCategory).orElseThrow(CategoryNotFoundException::new);
        category.setActive(0);
        category = categoryRepository.save(category);
        return mapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCatorys() {
        var list = categoryRepository.findAll();
        return list.stream().map(mapper::toCategoryResponse).toList();
    }

    @Override
    public CategoryResponse getCatoryById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return mapper.toCategoryResponse(category);
    }

    private void existName(String name) {
        if (categoryRepository.existsByName(name)){
            throw new NameAlreadyInUseException();
        }
    }

    private void existName(String name, Long id) {
        if (categoryRepository.existsByNameAndIdNot(name, id)){
            throw new NameAlreadyInUseException();
        }
    }
}
