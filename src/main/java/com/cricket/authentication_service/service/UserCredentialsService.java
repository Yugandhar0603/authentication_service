package com.cricket.authentication_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cricket.authentication_service.entity.UserCredentialsEntity;
import com.cricket.authentication_service.repository.UserCredentialsRepository;


@Service

public class UserCredentialsService {
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserCredentialsRepository authDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserCredentialsEntity register(UserCredentialsEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return authDao.saveAndFlush(user);
	}

	public String generateToken(String name, List<String> allRoles) {
		return jwtService.generateToken(name, allRoles);
	}

	public boolean verifyToken(String token) {
		jwtService.validateToken(token);
		return true;
	}

}

