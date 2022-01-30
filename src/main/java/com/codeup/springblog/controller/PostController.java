package com.codeup.springblog.controller;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.model.User;
import com.codeup.springblog.repository.PostRepository;
import com.codeup.springblog.service.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

	private final PostRepository postDao;
	private final EmailService emailservice;

	public PostController(PostRepository postDao, EmailService emailservice) {
		this.postDao = postDao;
		this.emailservice = emailservice;
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
		User postCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		post.setUser(postCreator);
		String postBody = "Your post has been created. \n" + post.getTitle() + ":\n" + post.getBody();
		emailservice.prepareAndSend(post, "Post Created", postBody);
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
