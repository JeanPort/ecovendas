package com.ppsolution.ecovendas.controller.admin;

import com.ppsolution.ecovendas.dto.request.CategoryRequest;
import com.ppsolution.ecovendas.dto.response.CategoryResponse;
import com.ppsolution.ecovendas.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/categories")
public class AdminCategoryRestController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        var response = categoryService.createCatory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId) {
        var response = categoryService.updateCategory(categoryId, categoryRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{categoryId}/disable")
    public ResponseEntity<CategoryResponse> disabledCategory(@PathVariable Long categoryId) {
        categoryService.disableCatory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
