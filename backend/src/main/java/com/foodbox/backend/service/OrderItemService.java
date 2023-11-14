package com.foodbox.backend.service;

import com.foodbox.backend.entity.OrderItem;
import com.foodbox.backend.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {


    private final OrderItemsRepository orderItemRepository;

    public OrderItemService(OrderItemsRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

      
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

      
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return orderItemRepository.findOrderItemByOrderId(orderId);
    }

    public OrderItem isOrderItem(int productId) {
        return orderItemRepository.findByProductId(productId);
}
      
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

      
    public OrderItem updateOrderItem(int orderItemId, OrderItem orderItem) {
        orderItem.setOrderItemId(orderItemId);
        return orderItemRepository.save(orderItem);
    }

      
    public void deleteOrderItem(Integer productId) {
        orderItemRepository.deleteOrderItemByProductId(productId);
    }

    public void deleteOrderItems() {orderItemRepository.deleteAll();
    }
}
