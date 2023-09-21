package com.blogprojlast.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.reflect.CatchClauseSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogprojlast.Dto.AddComment;
import com.blogprojlast.Model.Comment;
import com.blogprojlast.Model.Post;
import com.blogprojlast.Repo.CommentRepo;
import com.blogprojlast.Repo.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo ;
	
	@Autowired
	private PostRepo postRepo;

	@Override
	public String newCommentAdding(AddComment addComment) {
		
		Post post = postRepo.findByPostId(addComment.getPostId());
		
		Comment comment = Comment.builder()
				.addedName(addComment.getAddedName())
				.addedEmail(addComment.getAddedEmail())
				.content(addComment.getContent())
				.post(post)
				.build();
		
		commentRepo.save(comment);
		
		return "added successfully";
	}

	@Override
	public List<Comment> getCommentsByPostId(Integer postId) {
		
		List<Comment> comments= commentRepo.getCommentsByPostId(postId);
		
	
		
		return comments;
	}

	@Override
	public List<Comment> getCommentsByUserId(Integer userId) {
		
		List<Post> posts = postRepo.getAllPostsById(userId);
		
		
		List<Comment> comments = new ArrayList<>();
		
		for (Post post : posts) {
			  
		  comments.addAll(commentRepo.getCommentsByPostId(post.getPostId()));
			
		}
		
		
		
		return comments;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		commentRepo.deleteById(commentId);
		
	}
	
	

}
