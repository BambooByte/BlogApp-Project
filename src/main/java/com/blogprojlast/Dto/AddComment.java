package com.blogprojlast.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddComment {

	private String addedName;
	
	private String addedEmail;
	
	private String content;
	
	private Integer postId;
}
