package com.blogprojlast.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogprojlast.Model.User;



public interface UserRepo extends JpaRepository<User, Integer>{

	User findByUserEmail(String userEmail);

	User findByUserId(Integer id);

	

}
