package com.foodbox.backend.repository;

import com.foodbox.backend.entity.Order;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();

    @Query(value = "SELECT o.orderId from Order o WHERE o.customerId = :customerId")
    List<Integer> findOrderIdsByCustomerId(@Param("customerId") int customerId);

    @Override
    Order save(Order order);

    void deleteOrderByOrderId(Integer orderId);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM Order o WHERE o.customerId = :customerId")
    void deleteOrderByCustomerId(@Param("customerId") int customerId);
}