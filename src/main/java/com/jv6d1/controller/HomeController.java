package com.jv6d1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({"/","/home"})
	public String home() {
		return "redirect:/product/list";
	}
	
	@RequestMapping({"/admin","/admin/home"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
}
