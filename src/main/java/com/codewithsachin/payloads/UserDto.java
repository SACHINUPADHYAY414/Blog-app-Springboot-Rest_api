package com.codewithsachin.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="UserName must be min of 4 characters")
	private String name;
	
	@Email (message="Enter correct email address")
	private String email;
	
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z]{3})(?=.*\\d)(?=.*[@#$%^&+=])[A-Za-z\\d@#$%^&+=]{6,10}$", message = "Password must contain one uppercase letter, three lowercase letters, one special character, one digit, and be between 6 to 10 characters long")
	private String password;

	
	@NotEmpty
	private String about;
}
