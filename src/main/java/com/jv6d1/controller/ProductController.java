package com.jv6d1.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jv6d1.entity.Product;
import com.jv6d1.repository.ProductRepository;
import com.jv6d1.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService proService;
	
	@Autowired
	ProductRepository proRepo;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/list")
	public String list(Model model, @RequestParam("cid") Optional<String> cid) {
	
		
		
		String pageParam = request.getParameter("page");
		String limitParam = request.getParameter("limit");
		int page= pageParam == null ? 0 : Integer.parseInt(pageParam);
		int limit = limitParam == null ? 6 : Integer.parseInt(limitParam);
		if(page<0)
			page=0;
		Pageable pageable = PageRequest.of(page, limit);
		
		if(cid.isPresent()) {
			
		List<Product> list = proService.findByCategoryId(cid.get());
		model.addAttribute("tag",cid);
		model.addAttribute("items",list);
		}else {
		
		Page list = this.proService.findAll(pageable);
		
		model.addAttribute("page",page);
		model.addAttribute("items",list);	
	}
		
		return "product/list";
		
//		if(cid.isPresent()) {
//			List<Product> list = proService.findByCategoryId(cid.get());
//			model.addAttribute("items",list);
//		}else {
//			
//			List<Product> list = proService.findAll();
//			model.addAttribute("items",list);
//		}
//		return "product/list";
	}
	
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = proService.findById(id);
		model.addAttribute("item",item);
		return "product/detail";
	}
	
	@RequestMapping("/list/search")
	public String findByKeywords(Model model, @RequestParam("keywords") String keywords,
			@RequestParam("cid") Optional<String> cid) {
		
		List<Product> list = proService.fillByKeywords(keywords);
		model.addAttribute("tag",cid);
		model.addAttribute("items",list);
		return "product/list";
	}
}

