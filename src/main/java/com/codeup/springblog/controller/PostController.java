package com.codeup.springblog.controller;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.repository.PostRepository;
import com.codeup.springblog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

	private final PostRepository postDao;
	private final UserRepository userDao;

	public PostController(PostRepository postDao, UserRepository userDao) {
		this.postDao = postDao;
		this.userDao = userDao;
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
	public String viewCreatePost(Model model) {
		model.addAttribute("post", new Post());
		model.addAttribute("titleLabel", "Title: ");
		model.addAttribute("bodyLabel", "Body: ");

		return "posts/form";
	}

	@PostMapping("/posts/create")
	public String createPost(@ModelAttribute Post post) {
		post.setId(1L);
		postDao.save(post);

		return "redirect:/posts";
	}

	@GetMapping("/posts/edit/{id}")
	public String editPost(Model model, @PathVariable long id) {
		Post post = postDao.getById(id);
		model.addAttribute("post", post);
		model.addAttribute("titleLabel", "Edit Title: ");
		model.addAttribute("bodyLabel", "Edit Body: ");

		return "posts/form";
	}

	@PostMapping("posts/edit/{id}")
	public String saveEditPost(@ModelAttribute Post post) {
		postDao.save(post);

		return "redirect:/posts";
	}

	@PostMapping("/posts/delete/{id}")
	public String deletePostById(@PathVariable long id) {
		postDao.deleteById(id);

		return "redirect:/posts";
	}
}
