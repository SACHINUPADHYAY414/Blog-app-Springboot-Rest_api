package com.codewithsachin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsachin.entities.Category;
import com.codewithsachin.entities.Post;
import com.codewithsachin.entities.User;

public interface PostRepo extends JpaRepository <Post,Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
