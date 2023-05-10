package com.project.rcstore.controller;

import com.project.rcstore.exception.InformationExistException;
import com.project.rcstore.exception.InformationNotFoundException;
import com.project.rcstore.model.Product;
import com.project.rcstore.repository.ProductRepository;
import com.project.rcstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/products/")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping(path = "/products/{productId}")
    public Optional<Product> getProduct(@PathVariable Long productId){
        return productService.getProduct(productId);
    }


    /**
     * Create product method takes in product object and checks if name already exists. If not return new object saved.
     * @param productObject
     * @return
     */
    @PostMapping(path = "/products/")
    public Product createProduct(@RequestBody Product productObject) {
        return productService.createProduct(productObject);
    }

    @PutMapping(path = "/products/{productId}")
    public Product updateProduct(@PathVariable Long productId, Product productObject) {
        return productService.updateProduct(productId, productObject);
    }

    @DeleteMapping(path = "/products/{productId}")
    public Optional<Product> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

}
