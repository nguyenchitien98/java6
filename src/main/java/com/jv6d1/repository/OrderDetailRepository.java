package com.jv6d1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jv6d1.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
