package com.foodbox.backend.controller;

import com.foodbox.backend.entity.Customer;
import com.foodbox.backend.exception.NotFoundException;
import com.foodbox.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value="http://localhost:4200")
@RequestMapping(value = "/api")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        if((customerService.getAllCustomers() == null))throw new NotFoundException("Unable to fetch customers from the database");
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/customer/login")
    public int getCustomerByEmail(@RequestParam String email, @RequestParam String password) {
        Customer customer = customerService.getCustomerByEmail(email);
        if(customer.getPassword().equals(password))return customer.getCustomerId();
        else throw new NotFoundException("User Not Found!");
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customers/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
    }
}

