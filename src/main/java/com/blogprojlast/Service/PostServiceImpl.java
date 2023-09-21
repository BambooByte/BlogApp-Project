package com.blogprojlast.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogprojlast.Dto.AddPost;
import com.blogprojlast.Model.Post;
import com.blogprojlast.Model.User;
import com.blogprojlast.Repo.PostRepo;
import com.blogprojlast.Repo.UserRepo;
import com.blogprojlast.utils.AppConstants;

import jakarta.servlet.http.HttpSession;
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public String addNewPost(AddPost addPost) {
		
	Integer id= (Integer)	httpSession.getAttribute("userid");
	
	User user = userRepo.findByUserId(id);
		
		Post post = Post.builder()
				.postTitle(addPost.getPostTitle())
				.postShortDes(addPost.getShortDesc())
				.postContent(addPost.getContent())
				.user(user)
				.postStatus(AppConstants.ACTIVE_STR)
				.build();
		System.out.println(id);
		postRepo.save(post);
		
		return "New Post Added Successfully";
	}
	

	@Override
	public List<Post> getAllPostsByUserId(Integer userId) {
		
		List<Post> allPostsById = postRepo.getAllPostsById(userId);
		
		List<Post> posts= new ArrayList<>();
		
		for (Post post : allPostsById) {
			
			if(post.getPostStatus().equals(AppConstants.ACTIVE_STR)) {
				
				posts.add(post);
			}
			
		}
		
		return posts;
		
	
	}


	@Override
	public List<Post> getAllPosts() {
		
		 List<Post> all = postRepo.findAll();
		 
		 List<Post> posts= new ArrayList<>();
			
			for (Post post : all) {
				
				if(post.getPostStatus().equals(AppConstants.ACTIVE_STR)) {
					
					posts.add(post);
				}
				
			}
			
			return posts;
	}


	@Override
	public Post getPostContent(Integer postId) {
		
		 Post content = postRepo.getContentById(postId);
		
		return content;
	}


	@Override
	public List<Post> getPostsBySearch(String search) {
		
		List<Post> posts = postRepo.getBySearch(search);
		
		 List<Post> all= new ArrayList<>();
			
			for (Post post : posts) {
				
				if(post.getPostStatus().equals(AppConstants.ACTIVE_STR)) {
					
					all.add(post);
				}
				
			}
			
			return all;
		
		
	}


	@Override
	public Post getPostById(Integer postId) {
		
		Post post = postRepo.findByPostId(postId);
		
		return post;
	}


	@Override
	public String updatePost(Post post) {
		
		 Post existingPost = postRepo.findByPostId(post.getPostId());
		 if(AppConstants.ACTIVE_STR.equals(existingPost.getPostStatus()) && existingPost != null) {
	        
	            existingPost.setPostTitle(post.getPostTitle());
	            existingPost.setPostShortDes(post.getPostShortDes());
	            existingPost.setPostContent(post.getPostContent());
	            
	            postRepo.save(existingPost);
	        
		 }
		
		return "Updated successfully";
	}


	@Override
	public boolean deletePost(Integer postId) {
		
		Post post = postRepo.findByPostId(postId);
		post.setPostStatus("INACTIVE");
		
		postRepo.save(post);
		
		return true;
	}

}
