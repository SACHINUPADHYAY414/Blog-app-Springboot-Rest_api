package com.codewithsachin.service;

import java.util.List;

import com.codewithsachin.payloads.PostDto;
import com.codewithsachin.payloads.PostResponse;

public interface PostService {

	
	/////create 
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	
	///update
	PostDto updatePost(PostDto postDto, Integer PostId);

	///delete
	void deletePost(Integer postId);
	
	///get all posts
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy);
	
	
	/// get single post
	PostDto getPostById(Integer postId);
	
	/// get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	
	////search post
	List<PostDto> searchPosts(String keyword);
	
	
}
