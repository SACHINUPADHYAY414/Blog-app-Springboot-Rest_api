package com.codewithsachin.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithsachin.exceptions.*;
import com.codewithsachin.entities.User;
import com.codewithsachin.payloads.UserDto;
import com.codewithsachin.repositories.UserRepo;
import com.codewithsachin.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo userRepo; // Autowired UserRepo for database operations
	
	@Autowired
	private ModelMapper modelMapper;
	// Method to create a new user
	@Override
	public UserDto createUser(UserDto userDto) {
		// Convert UserDto to User entity
		User user=this.dtoToUser(userDto);
		// Save user to the database
		User saveduser= this.userRepo.save(user);
		// Convert the saved User entity back to UserDto and return
		return this.userToDo(saveduser);
	}
	
	// Method to update an existing user
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// Find the user by userId, if not found, throw ResourceNotFoundException
	    User user = this.userRepo.findById(userId)
	                             .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
	    // Update user details
	    user.setName(userDto.getName());
	    user.setEmail(userDto.getEmail());
	    user.setPassword(userDto.getPassword());
	    user.setAbout(userDto.getAbout());

	    // Save the updated user
	    User updatedUser = this.userRepo.save(user);
	    // Convert the updated User entity back to UserDto and return
	    return this.userToDo(updatedUser); 
	}

	// Method to get user by userId
	@Override
	public UserDto getUserById(Integer userId) {
		// Find the user by userId, if not found, throw ResourceNotFoundException
		User user = this.userRepo.findById(userId)
		                         .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
		// Convert User entity to UserDto and return
		return this.userToDo(user);
	}

	// Method to get all users
	@Override
	public List<UserDto> getAllUser() {
		// Get all users from the database
	    List<User> users = this.userRepo.findAll();
	    // Convert each User entity to UserDto and collect them into a list
	    List<UserDto> userDtos = users.stream()
	                                  .map(user -> userToDo(user))
	                                  .collect(Collectors.toList());
	    
	    return userDtos;
	}

	// Method to delete a user by userId
	@Override
	public void deleteUser(Integer userId) {
		// Find the user by userId, if not found, throw ResourceNotFoundException
		User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
		// Delete the user from the database
		this.userRepo.delete(user);
		
	}
	
	
//////ye kaam model mapper se krte hai to isse vi simple ho jayega 	 Qki modelmaper automatic oject ko map krta hai 
	
	// Method to convert UserDto to User entity
	private User dtoToUser(UserDto userDto) {
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		return user;
		
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	// Method to convert User entity to UserDto
	public UserDto userToDo(User user) {
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
	}



}
