package com.foodbox.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "orders", schema = "foodbox")
    public class Order {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "order_id")
        private int orderId;
        @Basic
        @Column(name = "customer_id")
        private int customerId;
        @Basic
        @Column(name = "order_date")
        private Timestamp orderDate;
        @Basic
        @Column(name = "order_total")
        private BigDecimal orderTotal;
        @Basic
        @Column(name = "shipping_address")
        private String shippingAddress;
        @Basic
        @Column(name = "billing_address")
        private String billingAddress;
    }
