package com.foodbox.backend.repository;

import com.foodbox.backend.entity.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Integer> {
    @Override
    List<Order> findAll();

 @Query("select o.orderId from Order o where o.customerId = :customerId")
 List<Integer> findOrderIdsByCustomerId(@Param("customerId") int customerId);
    
    @Override
    Order save(Order order);


    void deleteOrderByOrderId(Integer orderId);
}