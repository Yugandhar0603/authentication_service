package com.cricket.authentication_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolePojoCreate {
	private int id;
	private int userId;
	private int roleId;

}
