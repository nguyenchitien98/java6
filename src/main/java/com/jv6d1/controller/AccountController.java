package com.jv6d1.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jv6d1.entity.Account;
import com.jv6d1.entity.ChangeFormPassword;
import com.jv6d1.repository.AccountRepository;
import com.jv6d1.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService accService;
	
	@Autowired 
	AccountRepository accRepo;
	
	@Autowired 
	HttpServletRequest request;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Account> list = accService.findAll();
		model.addAttribute("listUser",list);
		return "user/list_user";
	}
	@GetMapping("/formchangePassword")
	public String formPass(Model model) {
		String uname= request.getRemoteUser();
		model.addAttribute("formPass",new ChangeFormPassword());
		model.addAttribute("message","Nhập đầy đủ các thông tin sau");
		model.addAttribute("username",uname);
		return "register/changePassword";
	}
	
	@PostMapping("/changePassword")
	public String changePass(Model model,@ModelAttribute("formPass") ChangeFormPassword formPass,
			@RequestParam("username") String username) {
		formPass.setUsername(username);
		Account account = accRepo.getAccount(formPass.getUsername(), formPass.getPassword());
		if(account==null) {
			model.addAttribute("message","Sai tài khoản rồi bạn ê");
		}else {
			if(!formPass.getConfirmPassword().equals(formPass.getNewPassword())) {
				model.addAttribute("message","Mật khẩu mới và nhập lại mật khẩu không trùng nhau");
			}else {
				accService.changePassword(formPass.getUsername(), formPass.getPassword(),formPass.getNewPassword());
				model.addAttribute("message","Đã thay đổi mật khẩu");
			}
		}
		
		return "register/changePassword";
	}
}
