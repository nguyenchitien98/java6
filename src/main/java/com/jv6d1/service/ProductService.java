package com.jv6d1.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jv6d1.entity.Product;

public interface ProductService {
	List<Product> findAll();
	
	Product findById(Integer id);
	
	Page<Product> findAll(Pageable page);

	List<Product> findByCategoryId(String cid);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);

	List<Product> fillByKeywords(String keywords);
	
	Page<Product> findByIdPage(String cid,Pageable page);



}
