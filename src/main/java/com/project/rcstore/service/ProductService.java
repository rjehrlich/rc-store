package com.project.rcstore.service;

import com.project.rcstore.exception.InformationExistException;
import com.project.rcstore.exception.InformationNotFoundException;
import com.project.rcstore.model.Product;
import com.project.rcstore.model.User;
import com.project.rcstore.repository.ProductRepository;
import com.project.rcstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Product> getProducts(){
        List<Product> products = productRepository.findByUserId(ProductService.getCurrentLoggedInUser().getId());
    }

    public Optional<Product> getProduct(@PathVariable Long productId){
        return productRepository.findById(productId);
    }

    public Product createProduct(@RequestBody Product productObject) {
        Product product = productRepository.findByName(productObject.getName());
        if (product !=null) {
            throw new InformationExistException("Product with name already exist. ");
        } else {
            return productRepository.save(productObject);
        }
    }

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

    public Optional<Product> deleteProduct(@PathVariable Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.deleteById(productId);
            return product;
        } else {
            throw new InformationNotFoundException("Product with Id " + productId + " not found");
        }
    }

    public Optional<Product> deleteProduct(@PathVariable Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.deleteById(productId);
            return product;
        } else {
            throw new InformationNotFoundException("Product with Id " + productId + " not found");
        }
    }

    public User createUser(@RequestBody User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User email already exist");
        }
    }
}
