package com.blogprojlast.Service;

import java.util.List;

import com.blogprojlast.Dto.AddComment;
import com.blogprojlast.Model.Comment;

public interface CommentService {
	
	String newCommentAdding(AddComment addComment);
	
	List<Comment> getCommentsByPostId(Integer postId);
	
	List<Comment> getCommentsByUserId(Integer userId);
	
	void deleteComment(Integer commentId);

}
