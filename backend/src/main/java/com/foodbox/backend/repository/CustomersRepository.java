package com.foodbox.backend.repository;

import com.foodbox.backend.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepository extends CrudRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();

    Customer findCustomerByCustomerId(int customerId);

    @Override
    Customer save(Customer customer);

    @Override
    void deleteById(Integer integer);

    Customer findCustomerByEmail(String email);
}