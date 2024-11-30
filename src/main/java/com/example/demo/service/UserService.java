package com.example.demo.service;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	

	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
	
	
	public User saveUser(User user)
	{
		
		System.out.println("Response 2");
		
		user.setUserpassword(bcrypt.encode(user.getUserpassword()));
		
		return userRepository.save(user);
		
	}
	
}
