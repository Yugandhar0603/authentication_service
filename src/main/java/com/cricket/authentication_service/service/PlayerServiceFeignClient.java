package com.cricket.authentication_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cricket.authentication_service.pojo.PlayerPojo;

@FeignClient(name = "players-service", url = "http://localhost:2024/api/players")
public interface PlayerServiceFeignClient {
	
	@PostMapping("/create")
    void createPlayer(@RequestBody PlayerPojo player);

}
