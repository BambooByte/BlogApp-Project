package com.blogprojlast.Service;

import java.util.List;

import com.blogprojlast.Dto.AddPost;
import com.blogprojlast.Model.Post;

public interface PostService {
	
	String addNewPost(AddPost addPost);
	
	String updatePost(Post post);
	
	List<Post> getAllPostsByUserId(Integer userId);
	
	List<Post> getAllPosts();
	
	Post getPostContent(Integer postId);
	
	Post getPostById(Integer postId);
	
	List<Post> getPostsBySearch(String search);
	
	boolean deletePost(Integer postId);

}
