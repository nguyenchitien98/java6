package com.jv6d1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jv6d1.entity.Role;
import com.jv6d1.repository.RoleRepository;
import com.jv6d1.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public List<Role> findAll() {
		
		return roleRepo.findAll();
	}

}
