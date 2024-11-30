package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.JWTService;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user)
	{
		System.out.println(user);
		User userObj = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(userObj);
	}
	
	
	@PostMapping("/login")
	public Object login(@RequestBody User user)
	{
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getUserpassword()));
		
		if(authenticate.isAuthenticated())
		{
				return jwtService.generateToken(user.getUsername());
		}
		else
		{
			return "Failure";
		}
		
		
	}
}


//
//{
//    "id": 189,
//    "username": "Harsh",
//    "userpassword": "Harsh@1234",
//    "role": "User"
//}
