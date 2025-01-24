package com.cricket.authentication_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cricket.authentication_service.entity.UserCredentialsEntity;
import com.cricket.authentication_service.pojo.UserRoleTokenPojo;
import com.cricket.authentication_service.pojo.UserRolesPojo;
import com.cricket.authentication_service.service.JwtService;
import com.cricket.authentication_service.service.UserCredentialsService;

	

@RestController
@RequestMapping("/api/auth")
public class UserCredentialsController {
	@Autowired
	JwtService jwtService;
	
	@Autowired
	private UserCredentialsService userCredService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public UserRolesPojo register(@RequestBody UserRolesPojo user) {
		return userCredService.register(user);
	}
	@GetMapping("/users")
	public List<UserCredentialsEntity> getAllUsers()
	{
		return userCredService.getAllUsers();
	}
	

	@GetMapping("/validate/token")
	public boolean validateToken(@RequestParam String token) {
		return userCredService.verifyToken(token);
	}

	@PostMapping("/validate/user")
	public UserRoleTokenPojo getToken(@RequestBody UserCredentialsEntity user) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		if (authenticate.isAuthenticated()) {
			System.out.println("isAuthenticated?" + authenticate.isAuthenticated());
			System.out.println("user authorities: " + authenticate.getAuthorities());
			List<String> allRoles = authenticate.getAuthorities().stream().map((role)->role.getAuthority()).toList();
			//return userCredService.generateToken(user.getName(), authenticate.getAuthorities().stream().map((role)->role.getAuthority()).toList());
			return (new UserRoleTokenPojo(user.getName(), allRoles, userCredService.generateToken(user.getName(),allRoles)));
		}
		return null;
	}
}
