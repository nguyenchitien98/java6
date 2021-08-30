
package com.jv6d1.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jv6d1.entity.Category;

import com.jv6d1.service.CategoryService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	@Autowired
	CategoryService cateService;
	
	@GetMapping()
	public List<Category> getAll() {
		return cateService.findAll();
	}
	

}
