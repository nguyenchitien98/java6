package com.jv6d1.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jv6d1.entity.Product;
import com.jv6d1.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService proService;
	
	@GetMapping()
	public List<Product> getAll() {
		return proService.findAll();
	}
	
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return proService.findById(id);
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return proService.create(product);
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id,@RequestBody Product product) {
		return proService.update(product);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 proService.delete(id);
	}
}
