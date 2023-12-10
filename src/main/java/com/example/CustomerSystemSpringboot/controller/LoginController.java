package com.example.CustomerSystemSpringboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.CustomerSystemSpringboot.entitys.MstUser;
import com.example.CustomerSystemSpringboot.repositories.LoginRepository;


@Controller
public class LoginController {
	 @Autowired
	 private LoginRepository loginRepository;
	
	@GetMapping("/")
	 public String home(Model model) {
		String userIdToTest = "1";
		String password = "123";
		MstUser result = loginRepository.findByUserIdAndPasswordAndDeleteYmdIsNull(userIdToTest, password);
		if (result != null) {
			//Chuyển sang search
		}
		model.addAttribute("mess", result);
        model.addAttribute("message", "Hello, Chao bạn!");
        return "index";
	 }
}
