package com.blogprojlast.Service;

import com.blogprojlast.Dto.Login;
import com.blogprojlast.Dto.Res;

public interface UserService {
	
	 String userSignUp(Res res);
	 
	 boolean userLogIn(Login login);
	 
	 

}
