package com.foodbox.backend.controller;

import com.foodbox.backend.entity.OrderItem;
import com.foodbox.backend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value="http://localhost:4200")
@RequestMapping(value="/api")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderItems")
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/orderItems/{customerId}")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable int customerId) {
        return orderItemService.getOrderItemsByOrderId(customerId);
    }


    @GetMapping("/isOrderItem/{productId}")
    public boolean isOrderItem(@PathVariable int productId) {
        return orderItemService.isOrderItem(productId) != null;
    }


    @PostMapping("/orderItems")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.createOrderItem(orderItem);
    }

    @PutMapping("/orderItems/{orderItemId}")
    public OrderItem updateOrderItem(@PathVariable int orderItemId, @RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(orderItemId, orderItem);
    }

    @DeleteMapping("/orderItems/{productId}")
    public void deleteOrderItem(@PathVariable("productId") int productId) {
        orderItemService.deleteOrderItem(productId);
    }

    @DeleteMapping("/orderItems")
    public void deleteOrderItems() {
            orderItemService.deleteOrderItems();
    }
}
