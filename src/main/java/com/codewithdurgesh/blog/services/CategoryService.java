package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer userId);

	CategoryDto getCategoryById(Integer categoryId);

	List<CategoryDto> getAllCategories();

	void deleteCategory(Integer categoryId);

}
