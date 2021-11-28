package com.jv6d1.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Accounts")
public class Account implements Serializable {
	@Id
	@NotBlank(message="Không được để trống username")
	String username;
	@NotBlank(message="Không được để trống password")
	@Size(min=6,max=12, message="Mật khẩu phải từ 6 đến 12 ký tự")
	String password;
	@NotBlank(message="Không được để trống fullname")
	String fullname;
	@NotBlank(message="Không được để trống email")
	@Email(message="email không đúng định dạng")
	String email;
	
	String photo;
	
	String reset_password_token;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
}
