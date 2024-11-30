package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
}
