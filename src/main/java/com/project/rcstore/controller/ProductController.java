package com.project.rcstore.controller;

import com.project.rcstore.exception.InformationExistException;
import com.project.rcstore.exception.InformationNotFoundException;
import com.project.rcstore.model.Product;
import com.project.rcstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/products/{productId}")
    public Optional<Product> getProduct(@PathVariable Long productId){
        return productRepository.findById(productId);
    }




    /**
     * Create product method takes in product object and checks if name already exists. If not return new object saved.
     * @param productObject
     * @return
     */
    @PostMapping(path = "/products/")
    public Product createProduct(@RequestBody Product productObject) {
        Product product = productRepository.findByName(productObject.getName());
        if (product !=null) {
            throw new InformationExistException("Product with name already exist. ");
        } else {
            return productRepository.save(productObject);
        }
    }

    @PutMapping(path = "/products/{productId}")
    public Product updateProduct(@PathVariable Long productId, Product productObject) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            if (productObject.getName().equals(product.get().getName())) {
                throw new InformationExistException("Product already exists ");
            } else {
                Product updateProduct = productRepository.findById(productId).get();
                updateProduct.setName(productObject.getName());
                updateProduct.setPrice(productObject.getPrice());
                updateProduct.setDescription(productObject.getDescription());
                updateProduct.setBrandName(productObject.getBrandName());
                updateProduct.setUrl(productObject.getUrl());
                return productRepository.save(productObject);
            }
        } else {
            throw new InformationNotFoundException(productId + "not found");
        }
    }
    @DeleteMapping(path = "/products/{productId}")
    public Optional<Product> deleteProduct(@PathVariable Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.deleteById(productId);
            return product;
        } else {
            throw new InformationNotFoundException("Product with Id " + productId + " not found");
        }
    }

}
