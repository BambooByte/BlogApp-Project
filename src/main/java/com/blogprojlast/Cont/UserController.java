package com.blogprojlast.Cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blogprojlast.Dto.AddPost;
import com.blogprojlast.Dto.Login;
import com.blogprojlast.Dto.Res;
import com.blogprojlast.Service.PostService;
import com.blogprojlast.Service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private HttpSession httpSession;
	

	@GetMapping("/signup")
    public String showSignupForm(Model model) {
    model.addAttribute("signupDto", new Res());
        return "signup";
    }

    @PostMapping("/sign")
    public String processSignupForm(@ModelAttribute("signupDto") Res res,
                                    Model model) {
     
    
	    	String message = service.userSignUp(res);

	        model.addAttribute("message", message);

     
        
    
        return "signup";
    }
    
    
    
    //Login.....
    
    @GetMapping("/login")
    public String showLoginPage(
            Model model) {
    	
    	model.addAttribute("loginForm", new Login());
    	
    	return "login";
    }
    
    

    @PostMapping("/login")
    public String processLoginForm( @ModelAttribute("loginForm") Login login,
                                    Model model  ) throws Exception {
    	
    	boolean message = service.userLogIn(login);
    	
    if (message) {
    	
    	 return "redirect:/dashboard";
    }
	
   
    model.addAttribute("msg", "Invalid Email or Password");
    
	return "login";
    	
    }
       
    
    
   
    @GetMapping("/logout")
    public String logOut()  {
    	
    	httpSession.invalidate();
    	
    	return "redirect:/";
    }
    
    
   
    
    
    
    
    

}
