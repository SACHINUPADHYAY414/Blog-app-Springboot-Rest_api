package com.codewithsachin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithsachin.payloads.ApiResponse;
import com.codewithsachin.payloads.CategoryDto;
import com.codewithsachin.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	
	///create
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory (@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	
	////update api
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto, @PathVariable("catId") Integer catId)
	{

		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
				
	}
	

	// API endpoint to delete a user by categoryId
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable("catId") Integer catId) {
		// Call the deleteCategory method from the UserService to delete the category
		categoryService.deleteCategory(catId);
		// Return a response indicating successful deletion
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is Deleted Successfully", true), HttpStatus.OK);
	}
	
	// API endpoint to get all category
	@GetMapping("/allCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		// Call the getAllCategory method from the CategoryService to retrieve all Category
		return ResponseEntity.ok(this.categoryService.getCategory());
	}
	
	
	// API endpoint to get a single user by catId
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer catId) {
		CategoryDto categoryDto = this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
}
