package com.foodbox.backend.service;

import com.foodbox.backend.entity.Order;
import com.foodbox.backend.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrdersRepository orderRepository;

    public OrderService(OrdersRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Integer> getOrderIdsByCustomerId(int customerId) {
        return orderRepository.findOrderIdsByCustomerId(customerId);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(int orderId, Order order) {
        order.setOrderId(orderId);
        return orderRepository.save(order);
    }

    public void deleteOrder(int orderId) {
        orderRepository.deleteOrderByOrderId(orderId);
    }
}
