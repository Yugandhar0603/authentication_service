package com.cricket.authentication_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cricket.authentication_service.entity.UserCredentialsEntity;
import com.cricket.authentication_service.service.UserRoleServiceFeignClient;

import com.cricket.authentication_service.repository.UserCredentialsRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserCredentialsRepository userCredentialsDao;

    @Autowired
    private UserRoleServiceFeignClient roleServiceFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<UserCredentialsEntity> user = userCredentialsDao.findByName(username); // Assuming username is unique
        System.out.println("user optional entity : " + user);
        if (user.isPresent()) {
        	List<String> allRoles = null;
            Integer userId = user.get().getId();

            
            List<String> roles;
            try {
                allRoles = roleServiceFeignClient.getRolesByUserId(userId);
                
            } catch (Exception e) {
                throw new UsernameNotFoundException("Failed to fetch roles for userId: " + userId, e);
            }
            System.out.println("allRoles : " + allRoles);
            return new CustomUserDetails(user.get(), allRoles);
        } else {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
    }
	

    
}
