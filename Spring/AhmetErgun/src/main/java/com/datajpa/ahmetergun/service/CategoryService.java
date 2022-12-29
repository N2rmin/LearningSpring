package com.datajpa.ahmetergun.service;

import com.datajpa.ahmetergun.dto.requestDto.CategoryRequestDto;
import com.datajpa.ahmetergun.dto.responseDto.CategoryResponseDto;
import com.datajpa.ahmetergun.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public Category getCategory(Long categoryId);
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    public CategoryResponseDto getCategoryById(Long categoryId);
    public List<CategoryResponseDto> getCategories();
    public CategoryResponseDto deleteCategory(Long categoryId);
    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto);


}
