package com.example.CustomerSystemSpringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
	@GetMapping("/Search")
	public String Search(Model model) {
		 return "Search";
	}
}
