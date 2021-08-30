package com.jv6d1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jv6d1.repository.OrderDetailRepository;
import com.jv6d1.service.OrderDetailService;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailRepository oderDetailRepo;


	
}
