package com.codewithsachin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsachin.entities.User;


public interface UserRepo extends JpaRepository<User, Integer> {

}
