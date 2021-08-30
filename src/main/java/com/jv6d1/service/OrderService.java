package com.jv6d1.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.jv6d1.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);

	List<Order> findByUssername(String username);

}
