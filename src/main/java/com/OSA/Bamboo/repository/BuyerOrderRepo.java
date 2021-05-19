package com.OSA.Bamboo.repository;

import com.OSA.Bamboo.model.BuyerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerOrderRepo extends JpaRepository<BuyerOrder, Long> {

}