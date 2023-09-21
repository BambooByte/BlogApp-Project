package com.blogprojlast.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogprojlast.Dto.AddPost;
import com.blogprojlast.Dto.Login;
import com.blogprojlast.Dto.Res;
import com.blogprojlast.Model.Post;
import com.blogprojlast.Model.User;
import com.blogprojlast.Repo.UserRepo;
import com.blogprojlast.utils.AppConstants;
import com.blogprojlast.utils.AppProperities;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private AppProperities props;

	@Override
	public String userSignUp(Res res) {
		
		 try {
				
				User mail = userRepo.findByUserEmail(res.getUserEmail());

			
		if(null != mail) {
		
			return props.getMessage().get(AppConstants.EMAIL_EXIST);
		
		}
		
		
		
		}catch (Exception e) {
		
			log.error(e.getMessage());
			
		}
		

			User userEntity = User.builder().userFirstName(res.getUserFirstName())
								.userLastName(res.getUserLastName())
								.userEmail(res.getUserEmail())
								.userPass(res.getUserPass())
								.build();
			
			userRepo.save(userEntity);
		
		return props.getMessage().get(AppConstants.RES_MSG);
	}

	@Override
	public boolean userLogIn(Login login) {
		 User ent = userRepo.findByUserEmail(login.getUserEmail());
		 try {
			 
		if(ent == null) {
			return false;
		}
		
		if(!ent.getUserPass().equals(login.getUserPass())) {
			return false;
		}
		
		}catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
	
		 httpSession.setAttribute("userid", ent.getUserId());
		 
		return true;
	}

	

}
