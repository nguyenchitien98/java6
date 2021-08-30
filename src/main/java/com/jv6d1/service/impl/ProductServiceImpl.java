package com.jv6d1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jv6d1.entity.Product;
import com.jv6d1.repository.ProductRepository;
import com.jv6d1.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository proRepo;

	@Override
	public List<Product> findAll() {
		
		return proRepo.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return proRepo.findById(id).get();
		
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		
		return proRepo.findByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {
		
		return proRepo.save(product);
	}

	@Override
	public Product update(Product product) {
		return proRepo.save(product);
	}

	@Override
	public void delete(Integer id) {
		
		 proRepo.deleteById(id);
	}
}
