package com.example.CustomerSystemSpringboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.CustomerSystemSpringboot.entitys.MstUser;
import com.example.CustomerSystemSpringboot.repositories.LoginRepository;


@Controller
public class LoginController {
	 @Autowired
	 private LoginRepository loginRepository;
	
	 @PostMapping("/login")
	 public ModelAndView  login(@RequestParam("userId") String userId,@RequestParam("passWord") String passWord, Model model){
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("Login");
		 
		 MstUser result = loginRepository.findByUserIdAndPasswordAndDeleteYmdIsNull(userId, passWord);

		 if (result != null) {
			 modelAndView.setViewName("Search");
		}
		 return modelAndView;
	 }
}
