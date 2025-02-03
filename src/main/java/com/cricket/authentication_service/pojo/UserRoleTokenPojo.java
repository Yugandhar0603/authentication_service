
package com.cricket.authentication_service.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleTokenPojo {
	private String name;
	private List<String> allRoles;
	private String token;
}
