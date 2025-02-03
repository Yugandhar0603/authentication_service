
package com.cricket.authentication_service.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricket.authentication_service.entity.UserCredentialsEntity;
import com.cricket.authentication_service.pojo.UserRolesPojo;
@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentialsEntity, Integer>{
	


	public Optional<UserCredentialsEntity> findByName(String username);

	public void save(UserRolesPojo user);

	public boolean existsByName(String name);

}
