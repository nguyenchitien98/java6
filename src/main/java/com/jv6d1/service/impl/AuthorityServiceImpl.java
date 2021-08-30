package com.jv6d1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jv6d1.entity.Account;
import com.jv6d1.entity.Authority;
import com.jv6d1.repository.AccountRepository;
import com.jv6d1.repository.AuthorityRepository;
import com.jv6d1.service.AuthorityService;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	AuthorityRepository authRepo;
	
	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountRepo.getAdministrators();
		return authRepo.authorritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		
		return authRepo.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		
		return authRepo.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authRepo.deleteById(id);
		
	}

	

}
