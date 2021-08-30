package com.jv6d1.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jv6d1.entity.Order;
import com.jv6d1.entity.OrderDetail;
import com.jv6d1.repository.OrderDetailRepository;
import com.jv6d1.repository.OrderRepository;
import com.jv6d1.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired 
	OrderDetailRepository orderDetailRepo;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
			
		Order order = mapper.convertValue(orderData, Order.class);
		orderRepo.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		orderDetailRepo.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Long id) {
		
		return orderRepo.findById(id).get();
	}

	@Override
	public List<Order> findByUssername(String username) {
		
		return orderRepo.findByUsername(username);
	}
}
