package com.codeup.springblog.controller;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	public String editPost(Model model, @PathVariable long id) {
		Post post = postDao.getById(id);
		model.addAttribute("post", post);

		return "posts/edit";
	}

	@PostMapping("posts/edit/{id}")
	public String saveEditPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @PathVariable long id) {
		Post post = postDao.getById(id);
		post.setTitle(title);
		post.setBody(body);
		postDao.save(post);

		return "redirect:/posts";
	}

	@PostMapping("/posts/delete/{id}")
	public String deletePostById(@PathVariable long id) {
		postDao.deleteById(id);

		return "redirect:/posts";
	}
}
