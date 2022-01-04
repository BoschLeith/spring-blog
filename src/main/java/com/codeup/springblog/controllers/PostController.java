package com.codeup.springblog.controllers;

import com.codeup.springblog.Post;
import com.codeup.springblog.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

	private final PostRepository postDao;

	public PostController(PostRepository postDao) {
		this.postDao = postDao;
	}

	@GetMapping("/posts")
	public String viewPosts(Model model) {
		model.addAttribute("Posts", postDao.findAll());

		return "posts/index";
	}

	@GetMapping("/posts/{id}")
	public String viewPostById(@PathVariable long id, Model model) {
		Post post = postDao.getById(id);
		model.addAttribute("Post", post);

		return "posts/show";
	}

	@GetMapping("/posts/create")
	@ResponseBody
	public String createForm() {
		return "view the form for creating a post";
	}

	@PostMapping("/posts/create")
	@ResponseBody
	public String createPost() {
		return "create a new post";
	}

	@GetMapping("/posts/edit/{id}")
	public String getPostById(Model model, @PathVariable String id) {
		Post post = postDao.getById(Long.valueOf(id));
		model.addAttribute("post", post);

		return "posts/edit";
	}

	@PostMapping("posts/edit/{id}")
	public String editPostById(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @PathVariable String id, Model model) {
		Post post = postDao.getById(Long.valueOf(id));
		post.setTitle(title);
		post.setBody(body);
		postDao.save(post);
		model.addAttribute("Posts", postDao.findAll());

		return "posts/index";
	}

	@GetMapping("/posts/delete/{id}")
	public String viewPostById(Model model, @PathVariable long id) {
		Post post = postDao.getById(id);
		model.addAttribute("post", post);

		return "posts/delete";
	}

	@PostMapping("/posts/delete/{id}")
	public String deletePostById(@PathVariable long id, Model model){
		postDao.deleteById(id);
		model.addAttribute("Posts", postDao.findAll());

		return "posts/index";
	}
}
