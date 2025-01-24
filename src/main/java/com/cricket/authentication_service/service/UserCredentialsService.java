package com.cricket.authentication_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.cricket.authentication_service.entity.UserCredentialsEntity;
import com.cricket.authentication_service.pojo.UserRolePojoCreate;
import com.cricket.authentication_service.pojo.UserRolesPojo;
import com.cricket.authentication_service.repository.UserCredentialsRepository;


@Service

public class UserCredentialsService {
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserRoleServiceFeignClient userRoleServiceFeignClient;
	
	@Autowired
	UserCredentialsRepository authDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserRolesPojo register(UserRolesPojo user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserCredentialsEntity newUser = authDao.saveAndFlush(new UserCredentialsEntity(0, user.getName(), user.getPassword()));
		
		for (int roleId : user.getAllRolesId()) {
            userRoleServiceFeignClient.createUserRole(new UserRolePojoCreate(0, newUser.getId(), roleId));
        }
		// use feign client and send the role ids and generated user id to user_roles table and insert
		
//		userRolesFeignClient.addUserRoles(request);
		
		return user;
	}

	public String generateToken(String name, List<String> allRoles) {
		return jwtService.generateToken(name, allRoles);
	}

	public boolean verifyToken(String token) {
		jwtService.validateToken(token);
		return true;
	}

	public List<UserCredentialsEntity> getAllUsers() {
		// TODO Auto-generated method stub
		return authDao.findAll();
	}

}

