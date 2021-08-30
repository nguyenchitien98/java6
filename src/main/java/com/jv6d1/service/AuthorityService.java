package com.jv6d1.service;

import java.util.List;

import com.jv6d1.entity.Authority;

public interface AuthorityService {

	public List<Authority> findAuthoritiesOfAdministrators();

	public List<Authority> findAll();

	public Authority create(Authority auth);
	
	public void delete(Integer id);
}
