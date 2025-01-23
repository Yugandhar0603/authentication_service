package com.cricket.authentication_service.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user_roles_service", url = "http://localhost:8083/api/user-roles")
public interface UserRoleServiceFeignClient {
	
	@GetMapping("/{userId}/roles")
    List<String> getRolesByUserId(@PathVariable Integer userId);

	
}
