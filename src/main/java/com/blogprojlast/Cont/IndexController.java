package com.blogprojlast.Cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blogprojlast.Model.Post;
import com.blogprojlast.Service.PostService;

@Controller
public class IndexController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/")
	public String show(Model model) {
		
		List<Post> list = postService.getAllPosts();
		
		model.addAttribute("list", list);
		
		return "index" ;
	}
	
	

}
