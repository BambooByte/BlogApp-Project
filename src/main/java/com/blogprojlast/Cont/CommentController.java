package com.blogprojlast.Cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blogprojlast.Dto.AddComment;
import com.blogprojlast.Model.Comment;
import com.blogprojlast.Model.Post;
import com.blogprojlast.Service.CommentService;
import com.blogprojlast.Service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private HttpSession httpSession;
	
	  @PostMapping(value = "/addComment")
	    public String addNewComment(Model model,
	    		
	    		@ModelAttribute("addComment") AddComment addComment) {
	    	model.addAttribute("addComment", new AddComment());
	    	
	    	String str = commentService.newCommentAdding(addComment);
	    	Post con = postService.getPostContent(addComment.getPostId());
	    	
	    	model.addAttribute("con", con);
	    	model.addAttribute("str", str);
	    	
	    	return "content";
	    }
	  
	  @GetMapping("/comment")
	  public String showCommentPage(Model model) {
		  
	Integer id=	(Integer)  httpSession.getAttribute("userid");
		  
	List<Comment> lists = commentService.getCommentsByUserId(id);
	
	model.addAttribute("lists", lists);
		  
		  return "comment";
	  }
	  
	  
	  
	  @GetMapping("/deleteComment/{id}")
	  public String deleteComment(@PathVariable(value = "id") Integer commentId, Model model) {
		 
		  commentService.deleteComment(commentId);
		  
		return  "redirect:/comment";
	  }

}
