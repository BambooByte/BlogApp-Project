package com.blogprojlast.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogprojlast.Model.Post;


public interface PostRepo extends JpaRepository<Post, Integer>{

	@Query("SELECT e FROM Post e WHERE e.user.userId = :userId")
	List<Post> getAllPostsById(@Param("userId") Integer userId);

	
	@Query("SELECT d FROM Post d WHERE d.postId = :postId")
	Post getContentById(Integer postId);


	Post findByPostId(Integer postId);


	@Query("SELECT p FROM Post p WHERE p.postTitle LIKE %:search%")
	List<Post> getBySearch(String search);

}
