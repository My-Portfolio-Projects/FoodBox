package com.foodbox.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodbox.backend.service.PaymentService;


@CrossOrigin(value="http://localhost:4200")
@RestController
@RequestMapping(value="/api")
public class PaymentController {
        @Autowired
        PaymentService paymentService;

@PostMapping("/payment/{customerId}")
public Boolean getPayment(@PathVariable("customerId") Integer customerId, @RequestBody Integer amount) {
        return this.paymentService.pay(customerId, amount);
        
}
}


