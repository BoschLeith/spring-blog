package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiceController {

	public static double getRandomRoll(double min, double max){
		double randomRoll = (int)(Math.random()*((max-min)+1))+min;
		return randomRoll;
	}

	@GetMapping("/roll-dice")
	public String showRollDice(){
		return "roll-dice";
	}

	@PostMapping("/roll-dice")
	public String guessRoll(@RequestParam(name = "userGuess") String userGuess, Model model) {
		model.addAttribute("userGuess", "User guess is " + userGuess);
		int randomRoll = (int) getRandomRoll(1, 6);
		model.addAttribute("randomRoll", "Random roll is " + randomRoll);
		int userInt = Integer.parseInt(userGuess);
		if (userInt == randomRoll) {
			System.out.println("Guess and Roll the same");
			model.addAttribute("correct", "You guess the same number!");
			return "roll-dice";
		}
		System.out.println(userGuess);
		System.out.println(randomRoll);
		model.addAttribute("correct", "You guess the wrong number!");
		return "roll-dice";
	}

}
