package com.foodbox.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.backend.entity.Payments;
import com.foodbox.backend.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Boolean pay(Integer customerId, Integer amount) {

        Payments payment = this.paymentRepository.findByCustomerId(customerId);
        if (payment.getBalance() < 0 && payment.getBalance() > amount) {
            return false;
        }
        
        else if (payment.getBalance() > 0 && payment.getBalance() >= amount) {
            payment.setBalance(payment.getBalance() - amount);
            Integer balance = payment.getBalance();
             this.paymentRepository.makePayment(balance, customerId);
            if (payment.getBalance().equals(balance)) {
                return true;
            }
           
        }
        return false;
    }
    
}
