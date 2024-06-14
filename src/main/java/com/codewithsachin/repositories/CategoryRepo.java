package com.codewithsachin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsachin.entities.Category;

public interface CategoryRepo  extends JpaRepository<Category,Integer>{

}
