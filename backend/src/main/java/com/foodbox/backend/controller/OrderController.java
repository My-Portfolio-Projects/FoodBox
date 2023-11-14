package com.foodbox.backend.controller;

import com.foodbox.backend.entity.Order;
import com.foodbox.backend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value="http://localhost:4200")
@RequestMapping(value="/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{customerId}")
    public List<Integer> getOrderIdsByCustomerId(@PathVariable  int customerId) {
        return orderService.getOrderIdsByCustomerId(customerId);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/orders/{orderId}")
    public Order updateOrder(@PathVariable  int orderId, @RequestBody Order order) {
        return orderService.updateOrder(orderId, order);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrder(@PathVariable  int orderId) {
        orderService.deleteOrder(orderId);
    }
}
