package com.jv6d1.controller;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jv6d1.entity.Account;
import com.jv6d1.service.AccountService;
import com.jv6d1.utils.UploadFileUtils;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	UploadFileUtils uploadUtil;
	
	@GetMapping()
	public String register(Model model) {
		model.addAttribute("account",new Account());
		model.addAttribute("message","Vui lòng nhập đầy đủ thông tin");
		
		return "/register/register";
	}
	
	@PostMapping("/create")
	public String save(Model model,@Validated @ModelAttribute("account") Account account
			,Errors error,@RequestParam("imageFile") MultipartFile uploadedFile
			) {
		if(error.hasErrors()) {
			model.addAttribute("massage","Sửa lại các lỗi sau");
			return "/register/register";
		}
		File fileimg=uploadUtil.handleUploadFile(uploadedFile);
		account.setPhoto(fileimg.getName());
		accountService.create(account);
		
		return "/register/success";
	}

}
