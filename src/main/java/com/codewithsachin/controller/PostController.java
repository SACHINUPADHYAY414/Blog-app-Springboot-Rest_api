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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithsachin.payloads.ApiResponse;
import com.codewithsachin.payloads.PostDto;
import com.codewithsachin.payloads.PostResponse;
import com.codewithsachin.payloads.UserDto;
import com.codewithsachin.service.PostService;



@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	///create post api
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost (
			@RequestBody PostDto PostDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		
		PostDto createPost = this.postService.createPost(PostDto,userId,categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	///get By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer userId)
	
	{
		List<PostDto> posts= this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	

	/////get By Category
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId)
	{

		List<PostDto> posts= this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}

	
	/// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse>getAllPost(
		
			@RequestParam(value= "pageNumber",defaultValue="1",required = false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="10",required = false)Integer pageSize,
			@RequestParam(value =" sortBy",defaultValue="postId ", required=false) String sortBy
			
			)
	
	{
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy);
	    return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	
	/// get post details by postId 
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto>getSinglePost(@PathVariable Integer postId)
	{
		return ResponseEntity.ok(this.postService.getPostById(postId));
	}

	
	/// delete post details by 
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ApiResponse("Post is Deleted !!!", true);
	}
	
	
	////update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId)
	
	{
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
		
	}
}
