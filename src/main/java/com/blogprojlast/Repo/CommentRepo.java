package com.blogprojlast.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blogprojlast.Model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	

	@Query("SELECT e FROM Comment e WHERE e.post.postId = :postId")
	List<Comment> getCommentsByPostId(Integer postId);

}
