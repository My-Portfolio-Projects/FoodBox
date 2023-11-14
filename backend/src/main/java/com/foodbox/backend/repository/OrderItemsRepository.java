package com.foodbox.backend.repository;

import com.foodbox.backend.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemsRepository extends CrudRepository<OrderItem, Integer> {
    @Override
    List<OrderItem> findAll();

    List<OrderItem> findOrderItemByOrderId(int orderId);

    @Override
    OrderItem save(OrderItem order);



    void deleteOrderItemByProductId(Integer productId);

    OrderItem findByProductId(int productId);
}