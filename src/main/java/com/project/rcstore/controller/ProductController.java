package com.project.rcstore.controller;

import com.project.rcstore.model.Product;
import com.project.rcstore.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class ProductController {
    private ProductRepository productRepository;

    @GetMapping(path = "/products/")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}
