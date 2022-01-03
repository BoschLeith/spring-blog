package com.codeup.springblog.controllers;

import com.codeup.springblog.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

	@GetMapping("/posts")
	public String viewPosts(Model model){
		Post post1 = new Post("Title1", "Body1");
		Post post2 = new Post("Title2", "Body2");
		ArrayList<Post> posts = new ArrayList<Post>();
		posts.add(post1);
		posts.add(post2);
		model.addAttribute("Posts", posts);
		return "posts/index";
	}

	@GetMapping("/posts/{id}")
	public String viewPostById(@PathVariable int id, Model model){
		Post post = new Post("Test Title", "Test Body");
		model.addAttribute("Post", post);
		return "posts/show";
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
