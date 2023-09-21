package com.blogprojlast.Cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blogprojlast.Dto.AddPost;
import com.blogprojlast.Dto.AddComment;
import com.blogprojlast.Model.Comment;
import com.blogprojlast.Model.Post;
import com.blogprojlast.Service.CommentService;
import com.blogprojlast.Service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private CommentService commentService;
	
	 @GetMapping("/addpost")
	    public String showAddPostPage( Model model  )  {
	    	
	    	model.addAttribute("addPost", new AddPost());
	    	
	    	return "addpost";
	    }
	    
	    @PostMapping("/blogs")
	    public String addPostPage( @ModelAttribute("addPost") AddPost addPost, Model model  )  {
	    	
	    	String string = postService.addNewPost(addPost);
	    	
	    	model.addAttribute("string", string);
	    	
	    	return "addpost";
	    }
	    
	    
	    @GetMapping("/dashboard")
	    public String showDashBoardPage(
	            Model model) {
	    	
	   Integer id= (Integer)httpSession.getAttribute("userid");
	    	
	    	List<Post> allPosts = postService.getAllPostsByUserId(id);
	    	
	    	model.addAttribute("allPosts", allPosts);
	    	
	    	return "dashboard";
	    }
	
	    @GetMapping(value = "viewPost/{postId}")
	    public String showContentPage(@PathVariable(name = "postId") Integer postId,Model model,
	    		
	    		@ModelAttribute("addComment") AddComment addComment) {
	    	
	    	Post con = postService.getPostContent(postId);
	    	List<Comment> comment = commentService.getCommentsByPostId(postId);
	    	
	    	model.addAttribute("comment", comment);
	    	model.addAttribute("con", con);
	    	
	    	return "content";
	    }
	  
	    
	    
	    @GetMapping("/editPost/{postId}")
	    public String editPost(Model model, @PathVariable(value = "postId") Integer postId) {
	    	

	        Post post = postService.getPostById(postId);
	        model.addAttribute("post", post);
	        return "update";
	    }
	    
	    @PostMapping("/updatePost")
	    public String editPostt(@ModelAttribute("post") Post post) {
	        postService.updatePost(post);
	        return "redirect:/dashboard";
	    }
	    
	    @GetMapping("/deletePost/{id}")
	    public String daletePost(@PathVariable(value = "id") Integer postId) {
	         postService.deletePost(postId);
	      
	        return "redirect:/dashboard";
	    }
	    
	    @GetMapping("/search")
	    public String search(@RequestParam("q") String query, Model model) {
	      List<Post> posts = postService.getPostsBySearch(query);
	      model.addAttribute("posts", posts);
	      return "index";
	    }

	    @GetMapping("/searchpost")
	    public String searchInside(@RequestParam("q") String query, Model model) {
	      List<Post> posts = postService.getPostsBySearch(query);
	      model.addAttribute("posts", posts);
	      return "dashboard";
	    }
}
