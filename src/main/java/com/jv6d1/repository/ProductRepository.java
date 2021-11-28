package com.jv6d1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jv6d1.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);
	
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	Page<Product> findByIdPage(String cid,Pageable page);
	
	
// Cach goi query 1:	
//	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
//			+ "OR p.category.id LIKE %?1% OR p.category.name LIKE %?1%")
	// Cach goji query 2:
	@Query("SELECT p FROM Product p WHERE "
			+ "CONCAT(p.name,p.category.id,p.category.name) LIKE %?1%")
	List<Product> fillByKeywords(String keyword);
}
