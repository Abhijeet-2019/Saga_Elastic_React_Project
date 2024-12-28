package com.segaExamples.OrderService.Repositories;

import com.segaExamples.OrderService.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositories extends JpaRepository<Orders,Long> {

}
