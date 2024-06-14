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
import com.codewithsachin.payloads.UserDto;
import com.codewithsachin.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userService; // Autowired UserService for handling user-related operations
	

	////home api
    @GetMapping("")
    public ResponseEntity<String> responseEntity() {
        return ResponseEntity.ok("Server Running");
    }

	
	// API endpoint to create a new user
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		// Call the createUser method from the UserService and return the created user
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	// API endpoint to update an existing user
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
		// Call the updateUser method from the UserService and return the updated user
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	// API endpoint to delete a user by userId
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") Integer userId) {
		// Call the deleteUser method from the UserService to delete the user
		userService.deleteUser(userId);
		// Return a response indicating successful deletion
		return new ResponseEntity<>(new ApiResponse("User is Deleted Successfully", true), HttpStatus.OK);
	}

	// API endpoint to get all users
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		// Call the getAllUser method from the UserService to retrieve all users
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	// API endpoint to get a single user by userId
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		// Call the getUserById method from the UserService to retrieve the user by userId
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
