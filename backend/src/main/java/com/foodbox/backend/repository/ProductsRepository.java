package com.foodbox.backend.repository;

import com.foodbox.backend.entity.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();

    List<Product> findProductByNameContaining(String name);

    Product findProductByProductId(int productId);


    @Query("select p.name from Product p where p.productId = :productId")
    String findNameByProductId(int productId);

    Product save(Product product);

    void deleteProductByProductId(Integer integer);


}