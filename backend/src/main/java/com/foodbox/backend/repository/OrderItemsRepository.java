package com.foodbox.backend.repository;

import com.foodbox.backend.entity.OrderItem;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderItemsRepository extends CrudRepository<OrderItem, Integer> {
    List<OrderItem> findAll();

    List<OrderItem> findOrderItemByOrderId(int orderId);

    OrderItem save(OrderItem order);



    void deleteOrderItemByProductId(Integer productId);

    OrderItem findByProductId(int productId);
}