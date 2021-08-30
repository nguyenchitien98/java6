package com.jv6d1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jv6d1.entity.Account;
import com.jv6d1.repository.AccountRepository;
import com.jv6d1.service.AccountService;

@Service
public class AccountImpl implements AccountService {
	@Autowired
	AccountRepository accountRepo;

	@Override
	public Account findById(String username) {
		
		return accountRepo.findById(username).get();
	}

	@Override
	public List<Account> getAdministrators() {
		
		return accountRepo.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		
		return accountRepo.findAll();
	}

	
	
}
