package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.UserDetailService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	JWTFilter jwtFilter;
	
	
	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf(customizer -> customizer.disable())
				   .authorizeHttpRequests(requests -> requests
	                        .requestMatchers("/register", "/login").permitAll() 
	                        .anyRequest().authenticated()            
	                )
				   .httpBasic(Customizer.withDefaults())
				   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				   .build();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		UserDetails user = User.withDefaultPasswordEncoder()
//							   .username("khushi")
//							   .password("khushi1234")
//							   .roles("USER")
//							   .build();
//		
//		UserDetails admin = User.withDefaultPasswordEncoder()
//				                .username("admin")
//				                .password("adin12345")
//				                .roles("admin")
//				                .build();
//		
//		return new InMemoryUserDetailsManager(user, admin);
//		
//	}
	
}
