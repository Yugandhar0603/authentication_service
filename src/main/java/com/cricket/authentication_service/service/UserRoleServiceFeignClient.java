
package com.cricket.authentication_service.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cricket.authentication_service.pojo.UserRolePojoCreate;

@FeignClient(name = "user-roles-service", url = "http://localhost:8083/api/user-roles")
public interface UserRoleServiceFeignClient {
	
	@GetMapping("/{userId}/roles")
    List<String> getRolesByUserId(@PathVariable Integer userId);
	@PostMapping("/posting")
    public UserRolePojoCreate createUserRole(@RequestBody UserRolePojoCreate entity);

	
}
