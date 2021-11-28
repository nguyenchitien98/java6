package com.jv6d1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeFormPassword {
	String username;
	String password;
	String confirmPassword;
	String newPassword;
}
