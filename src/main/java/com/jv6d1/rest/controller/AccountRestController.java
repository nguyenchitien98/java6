
package com.jv6d1.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jv6d1.entity.Account;
import com.jv6d1.service.AccountService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
	@GetMapping()
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
		
	}
	

}
