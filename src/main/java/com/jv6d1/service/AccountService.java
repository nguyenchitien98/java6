package com.jv6d1.service;

import java.util.List;

import com.jv6d1.entity.Account;

public interface AccountService {

	public Account findById(String username);

	public List<Account> getAdministrators();

	public List<Account> findAll();
	
}
