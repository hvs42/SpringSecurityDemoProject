package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

class Temp
{
	int a;
	String name;
}

@RestController
public class MainController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/book")
	public Object getBook()
	{
		Temp obj = new Temp();
		obj.a = 2;
		obj.name = "harsh";
		return ResponseEntity.status(200);
	}

	@GetMapping("/home")
	public ResponseEntity<String> getHomeData()
	{
		return ResponseEntity.ok("Hello World");
	}
	
	
	
	
	@GetMapping("/about")
	public ResponseEntity<String> getAboutPageData()
	{
		return ResponseEntity.ok("About Page");
	}
}
