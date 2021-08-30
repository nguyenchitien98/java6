package com.jv6d1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jv6d1.entity.Category;
import com.jv6d1.repository.CategoryRepository;
import com.jv6d1.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository cateRepo;

	@Override
	public List<Category> findAll() {
		
		return cateRepo.findAll();
	}
}
