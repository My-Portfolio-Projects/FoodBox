package com.foodbox.backend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodbox.backend.entity.Payments;

import jakarta.transaction.Transactional;

@Repository
public interface PaymentRepository extends CrudRepository<Payments, Integer> {

    Payments findByCustomerId(Integer customerId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Payments p SET p.balance = :balance WHERE p.customer_id = :customer_id", nativeQuery = true)
    void makePayment(@Param("balance") Integer amount, @Param("customer_id") Integer customerId);
}
