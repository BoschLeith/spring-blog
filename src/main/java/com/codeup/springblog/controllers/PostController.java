package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

	@GetMapping("/posts")
	@ResponseBody
	public String viewPosts(){
		return "posts index page";
	}

	@GetMapping("/posts/{id}")
	@ResponseBody
	public String viewPostById(@PathVariable int id){
		return "view an individual post for postId " + id;
	}

	@GetMapping("/posts/create")
	@ResponseBody
	public String createForm(){
		return "view the form for creating a post";
	}

	@PostMapping("/posts/create")
	@ResponseBody
	public String createPost(){
		return "create a new post";
	}

}
