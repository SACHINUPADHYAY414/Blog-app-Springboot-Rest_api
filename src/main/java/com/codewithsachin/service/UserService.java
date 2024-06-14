package com.codewithsachin.service;

import java.util.List;

import com.codewithsachin.payloads.UserDto;


public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer UserId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();

	void deleteUser(Integer userId);
	
}
