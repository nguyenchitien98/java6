package com.jv6d1.service.impl;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.jv6d1.entity.Account;
import com.jv6d1.repository.AccountRepository;
import com.jv6d1.service.AccountService;

@Service
public class AccountImpl implements AccountService {
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@Override
	public Account findById(String username) {
		
		return accountRepo.findById(username).get();
	}

	@Override
	public List<Account> getAdministrators() {
		
		return accountRepo.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		
		return accountRepo.findAll();
	}

	@Override
	public Account create(Account account) {
		
		return accountRepo.save(account);
	}

	@Override
	public Account update(Account account) {
		
		return accountRepo.save(account);
	}

	@Override
	public void delete(String username) {
		
		accountRepo.deleteById(username);
	}
	
	@Override
	public Account registerNewUserAccount(Account account) {
		passwordEncoder.encode(account.getPassword());
		return accountRepo.save(account);
	}

	@Override
	public Account changePassword(String username, String oldPassword, String newPassword){
		Account account= accountRepo.getAccount(username, oldPassword);
//		if(account==null) {
//			return null; 
//		}
		account.setPassword(newPassword);
		return accountRepo.save(account);
		
	}

	@Override
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
//		String fullname = oauth2.getPrincipal().getAttribute("name");
		String email= oauth2.getPrincipal().getAttribute("email");
		String password= Long.toHexString(System.currentTimeMillis());
		
		UserDetails user = User.withUsername(email)
			.password(passwordEncoder.encode(password)).roles("CUST").build();
		UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth); // Dùng authen của mạng xã hội
		
	}

	// Phuc vu gui mail
	@Override
	public void updateResetPassWordToken(String token, String email) throws AccountNotFoundException {
		Account account = accountRepo.findByEmail(email);
		if(account!=null) {
			account.setReset_password_token(token);
			accountRepo.save(account);
		}else {
			throw new AccountNotFoundException("Could not find any customer with the email" + email);
		}	
	}

	@Override
	public Account getByResetPasswordToken(String token) {
		
		return accountRepo.findByResetPasswordToken(token);
	}

	@Override
	public void updatePassword(Account account, String newPassword) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodePassword = passwordEncoder.encode(newPassword);
//		account.setPassword(encodePassword);
		
		account.setPassword(newPassword);
		account.setReset_password_token(null);
		accountRepo.save(account);
		
	}
}
