package com.OSA.Bamboo.repository;

import com.OSA.Bamboo.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Long> {

}