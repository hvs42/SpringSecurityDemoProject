package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserPrincipal;
import com.example.demo.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByUsername(username);
		
		if(user == null)
		{
			System.out.println("User 404");
			throw new UsernameNotFoundException("User 404");
		}
		
//		user.setUserpassword();
		
		return new UserPrincipal(user);
	}

}
