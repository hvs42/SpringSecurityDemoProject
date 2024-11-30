package com.example.demo.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	String secretKey;
	
	public JWTService() {
		// TODO Auto-generated constructor stub
		
		secretKey = generateSecretKey();
	}
	
	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(String userName)
	{
		Map<String, Object> claims = new HashMap();
		
		return Jwts.builder()
						.setClaims(claims)
						.setSubject(userName)
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 1000*60*1))
						.signWith(getKey(), SignatureAlgorithm.HS256).compact();
	}
	
	public String generateSecretKey() {
		
		try
		{
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			
			SecretKey secretKey = keyGen.generateKey();
			
			System.out.println("Secret Key : " + secretKey.getEncoded());
			
			return Base64.getEncoder().encodeToString(secretKey.getEncoded());
			
		}catch(NoSuchAlgorithmException e) {
				throw new RuntimeException("Error generating secret key", e);
		}
	}

	public String extractUserName(String token) {
		
		return getClaims(token).getSubject();
	}
	
	public Claims getClaims(String token)
	{
		return Jwts.parser()
						.setSigningKey(getKey())
						.build()
						.parseClaimsJws(token)
						.getBody();
	}
	
	public boolean verifyExpiryTime(String token)
	{
		return (getClaims(token).getExpiration()).after(new Date(System.currentTimeMillis()));
	}
	
	//userDetails.username matched with token userName,Expirytime valid
	public boolean jwtTokenVerify(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return (verifyExpiryTime(token) && userDetails.getUsername().equals(extractUserName(token)));
	}

	



}

//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJzaHMiLCJpYXQiOjE3MzI5OTU2MDksImV4cCI6MTczMjk5NTc4OX0.ofTiEb75UWOgTZD4d-ppRXAN1ArunUSPPTZOgyjUciA
