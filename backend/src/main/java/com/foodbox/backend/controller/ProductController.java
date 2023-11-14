


package com.foodbox.backend.controller;

import com.foodbox.backend.entity.Product;
import com.foodbox.backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search/{keyword}")
    public List<Product> searchProducts(@PathVariable("keyword") String keyword) {
        return productService.getProductsByName(keyword);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductByProductId(productId);
    }
     @GetMapping("/productname/{productId}")
    public String getProductNameById(@PathVariable int productId) {
        return productService.getNameByProductId(productId);
    }

    @PostMapping(value="/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }
}

