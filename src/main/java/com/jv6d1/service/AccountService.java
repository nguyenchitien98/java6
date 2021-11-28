package com.jv6d1.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.jv6d1.entity.Account;

public interface AccountService {

	public Account findById(String username);

	public List<Account> getAdministrators();

	public List<Account> findAll();

	public Account create(Account account);

	public Account update(Account account);

	public void delete(String username);

	Account registerNewUserAccount(Account account);
	
	Account changePassword(String username, String oldPassword,String newPassword);

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2);
	
	// Phuc vu gui mail
	public void updateResetPassWordToken(String token,String email) throws AccountNotFoundException;
	
	public Account getByResetPasswordToken(String token);
	
	public void updatePassword(Account account,String newPassword);
}
