package com.jv6d1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.jv6d1.entity.Account;


public interface AccountRepository extends JpaRepository<Account, String> {
	
	// DISTINCT : Loại bỏ các bản ghi trùng lặp
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE','STAF')")
	List<Account> getAdministrators();

	@Query("SELECT a FROM Account a WHERE a.username =?1 and a.password=?2")
	Account getAccount(String username,String password);
	
	// Phuc vu viec gui mail
	@Query("SELECT a FROM Account a WHERE a.email=?1")
	public Account findByEmail(String email);
	
	@Query("SELECT a FROM Account a WHERE a.reset_password_token=?1")
	public Account findByResetPasswordToken(String token);
	
	
}
