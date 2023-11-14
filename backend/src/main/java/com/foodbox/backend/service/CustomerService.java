package com.foodbox.backend.service;

import com.foodbox.backend.entity.Customer;
import com.foodbox.backend.repository.CustomersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {

    private final CustomersRepository customerRepository;


    public CustomerService(CustomersRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Customer getCustomerById(int customerId) {
        return customerRepository.findCustomerByCustomerId(customerId);
    }


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer updateCustomer(int customerId, Customer customer) {
        customer.setCustomerId(customerId);
        return customerRepository.save(customer);
    }


    public void deleteCustomer(int customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
