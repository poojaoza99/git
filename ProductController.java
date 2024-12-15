package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.ProductService;
import com.example.demo.entity.Product;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. GET all Products with pagination
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    // 2. POST - Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // 3. GET - Get product by ID (with category details)
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // 4. PUT - Update product by ID
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    // 5. DELETE - Delete product by ID
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "Product deleted successfully";
        
    }
}
