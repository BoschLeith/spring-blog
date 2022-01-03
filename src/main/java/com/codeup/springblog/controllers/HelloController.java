package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

	@GetMapping("/hello")
	@ResponseBody
	public String hello(){
		return "Hello from Spring!";
	}

	@GetMapping("/hello/{name}")
	@ResponseBody
	public String sayHello(@PathVariable String name){
		return "Hello " + name + "!";
	}

	@RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
	@ResponseBody
	public String addOne(@PathVariable int number){
		return "Your number plus one is " + (number + 1);
	}

	@GetMapping("/join")
	public String showJoinForm() {
		return "join";
	}

	@PostMapping("/join")
	public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
		model.addAttribute("cohort", "Welcome to " + cohort + "!");
		return "join";
	}

}
