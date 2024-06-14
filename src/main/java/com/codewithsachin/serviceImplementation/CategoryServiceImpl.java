package com.codewithsachin.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithsachin.entities.Category;
import com.codewithsachin.exceptions.ResourceNotFoundException;
import com.codewithsachin.payloads.CategoryDto;
import com.codewithsachin.repositories.CategoryRepo;
import com.codewithsachin.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService  {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat= this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category"," Category Id",categoryId.toString()));
			cat.setCategoryTitle(categoryDto.getCategoryTitle());
		    cat.setCategoryDescription(categoryDto.getCategoryDescription()); 
			
		    Category updatecat = this.categoryRepo.save(cat);
		    return this.modelMapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category"," Category Id",categoryId.toString()));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
	    Category cat = this.categoryRepo.findById(categoryId)
	                           .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId.toString()));
	    return modelMapper.map(cat, CategoryDto.class);
	}


	@Override
	public List<CategoryDto> getCategory() {
		List<Category> category= this.categoryRepo.findAll();
		
		List<CategoryDto> catDto = category.stream()
			    .map(cat -> this.modelMapper.map(cat, CategoryDto.class))
			    .collect(Collectors.toList());

		 return catDto;
	}

}
