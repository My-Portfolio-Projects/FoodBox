package com.foodbox.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="payments")
public class Payments {

    @Id
    @Column(name="payment_id")
    Integer paymentId;

    @Column(name = "customer_id")
    Integer customerId;
    
    @Column(name="name")
    String name;

    @Column
    Integer balance;


}
