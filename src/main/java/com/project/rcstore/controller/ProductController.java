package com.project.rcstore.controller;

import com.project.rcstore.model.Product;
import com.project.rcstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/products/")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping(path = "/products/")
    public Product createProduct(@RequestBody Product productObject) {
        Product product = productRepository.findByBrandName(productObject.getBrandName());
        if (product !=null) {

        }
    }
}
