
package com.jv6d1.rest.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jv6d1.entity.Authority;
import com.jv6d1.service.AuthorityService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {
	@Autowired
	AuthorityService authoService; 
	
	@GetMapping
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return authoService.findAuthoritiesOfAdministrators();
		}
		return authoService.findAll();
	}
	
	@PostMapping
	public Authority post(@RequestBody Authority auth) {
		return authoService.create(auth);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		authoService.delete(id);
	}

}
