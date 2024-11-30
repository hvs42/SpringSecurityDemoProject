package com.example.demo.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "userdetails")
@Getter
@Setter
@ToString
public class User {

	@Id
	int id;
	
	String username;
	
	String userpassword;
	
	String role;
	
}
