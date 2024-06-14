package com.codewithsachin.serviceImplementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithsachin.entities.Category;
import com.codewithsachin.entities.Post;
import com.codewithsachin.entities.User;
import com.codewithsachin.exceptions.ResourceNotFoundException;
import com.codewithsachin.payloads.PostDto;
import com.codewithsachin.payloads.PostResponse;
import com.codewithsachin.repositories.CategoryRepo;
import com.codewithsachin.repositories.PostRepo;
import com.codewithsachin.repositories.UserRepo;
import com.codewithsachin.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	//// createPost
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
	
	User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId.toString()));
				 
	Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId.toString()));
			    
		Post post= this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		
		Post newPost= this.postRepo.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class );
		
	}
	
	////getPostsByCategory
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
	    Category cat = this.categoryRepo.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId.toString()));
	    List<Post> posts = this.postRepo.findByCategory(cat);
	    
	    List<PostDto> postDtos = posts.stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    return postDtos;
	}


	/// getPostsByUser
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		  
		User user = this.userRepo.findById(userId)
		            .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId.toString()));
		
		    List<Post> posts = this.postRepo.findByUser(user);
		    
		    ///convert post into postdto.class
		    List<PostDto> postDtos = posts.stream()
		            .map(post -> this.modelMapper.map(post, PostDto.class))
		            .collect(Collectors.toList());

		    return postDtos;
	}

	//// get all post
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
	    // Get all posts from the database

		Pageable p=PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
		
	    Page<Post> pagePost = this.postRepo.findAll(p);
	    List<Post> allPosts= pagePost.getContent();
	    // Convert posts to DTOs
	   
	    List<PostDto> postDto = allPosts.stream()
	                                    .map(post -> this.modelMapper.map(post, PostDto.class))
	                                    .collect(Collectors.toList());
	    
	    PostResponse postResponse = new PostResponse();
	    
	    postResponse.setContent(postDto);
	    postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setPageSize(pagePost.getSize());
	    postResponse.setTotalPages(pagePost.getTotalPages());
	    postResponse.setTotalElements(pagePost.getTotalElements());
	    postResponse.setLastPage(pagePost.isLast());
		
	    return postResponse;
	    
	    
	}

	
	////get post by postId
	@Override
	public PostDto getPostById(Integer postId) {
	
		Post post= this.postRepo.findById(postId) .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId.toString()));
		
		return this.modelMapper.map(post, PostDto.class);
	}


	///delete post
	@Override
	public void deletePost(Integer postId)
	{
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId.toString()));
		this.postRepo.delete(post);
	}


	/////   search Posts
	@Override
	public List<PostDto> searchPosts(String keyword) {
	
		
		return null;
	}
	
	///// update post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId.toString()));
		this.postRepo.delete(post);
	
//		post.setAddedDate(postDto.getAddedDate());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		post.setTitle(postDto.getTitle());

		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

}
