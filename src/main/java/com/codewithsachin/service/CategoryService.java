package com.codewithsachin.service;

import java.util.List;

import com.codewithsachin.payloads.CategoryDto;


public interface CategoryService {

	///create category
	CategoryDto createCategory(CategoryDto categoryDto);
	
	///update category
	CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
	
	
	
	//delete category
	public void deleteCategory(Integer categoryId);

		
	///get 
	CategoryDto getCategory(Integer categoryId);
	
	
	///get all
	List<CategoryDto> getCategory();

}
