package com.foodbox.backend.service;

import com.foodbox.backend.entity.Product;
import com.foodbox.backend.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductsRepository productRepository;

    public ProductService(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByProductId(int productId) {
        return productRepository.findProductByProductId(productId);
    }

    public String getNameByProductId(int productId) {
        return productRepository.findNameByProductId(productId);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int productId, Product product) {
        product.setProductId(productId);
        return productRepository.save(product);
    }

    public void deleteProduct(int productId) {
        productRepository.deleteProductByProductId(productId);
    }

    public List<Product> getProductsByName(String productName) {
        return productRepository.findProductByNameContaining(productName);
    }
}