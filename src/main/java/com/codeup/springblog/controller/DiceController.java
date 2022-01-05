package com.codeup.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiceController {

	public static double getRandomRoll(double min, double max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

	@GetMapping("/roll-dice")
	public String showRollDice() {
		return "roll-dice";
	}

	@PostMapping("/roll-dice")
	public String guessRoll(@RequestParam(name = "userGuess") String userGuess, Model model) {
		int userInt = Integer.parseInt(userGuess);
		int randomRoll = (int) getRandomRoll(1, 6);

		System.out.println(userGuess);
		System.out.println(randomRoll);

		model.addAttribute("userGuess", "User guess is " + userGuess);
		model.addAttribute("randomRoll", "Random roll is " + randomRoll);

		if (userInt == randomRoll) {
			System.out.println("userGuess and randomRoll the same");
			model.addAttribute("message", "You guess the same number!");
			return "roll-dice";
		}

		model.addAttribute("message", "You guess the wrong number!");
		return "roll-dice";
	}

}
