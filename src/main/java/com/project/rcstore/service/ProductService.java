package com.project.rcstore.service;

import com.project.rcstore.exception.InformationExistException;
import com.project.rcstore.exception.InformationNotFoundException;
import com.project.rcstore.model.Product;
import com.project.rcstore.model.User;
import com.project.rcstore.repository.ProductRepository;
import com.project.rcstore.repository.UserRepository;
import com.project.rcstore.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    /**
     * getCurrentLoggedInUser allows access to current logged in users information
     * by using the SecurityContext method and
     * @return that user from the userDetails instance
     */
    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findByUserId(ProductService.getCurrentLoggedInUser().getId());
        if (products.isEmpty()) {
            throw new InformationNotFoundException("no products found for user id " + ProductService.getCurrentLoggedInUser().getId());
        } else {
            return products;
        }
    }

    public Optional<Product> getProduct(Long productId) {
        Product product = productRepository.findByIdAndUserId(productId, ProductService.getCurrentLoggedInUser().getId());
        if (product == null) {
            throw new InformationNotFoundException("Product with id " + productId + " not found");
        } else {
            return Optional.of(product);
        }
    }

    /**
     * createProduct initialized a new product object and uses the custom findByUserIdAndName
     * method from the productRepository to then validate the current logged-in user. By passing in the:
     * @param productObject
     * @return a newly created product object if the user does not create one that already has the same name.
     */
    public Product createProduct(Product productObject) {
        Product product = productRepository.findByUserIdAndName(ProductService.getCurrentLoggedInUser().getId(), productObject.getName());
        if (product != null) {
            throw new InformationExistException("Product with name already exist. ");
        } else {
            // set the current logged-in user to product object before saving to db
            productObject.setUser(getCurrentLoggedInUser());
            return productRepository.save(productObject);

        }
    }

    public Product updateProduct(Long productId, Product productObject) {
        Product product = productRepository.findByIdAndUserId(productId, ProductService.getCurrentLoggedInUser().getId());
        if (product == null) {
                throw new InformationNotFoundException(productId + "not found");
        } else {
            product.setName(productObject.getName());
            product.setPrice(productObject.getPrice());
            product.setDescription(productObject.getDescription());
            product.setBrandName(productObject.getBrandName());
            product.setUrl(productObject.getUrl());
            product.setUser(ProductService.getCurrentLoggedInUser());
            return productRepository.save(product);
        }
    }


    public Optional<Product> deleteProduct(Long productId) {
        Optional<Product> product = getProduct(productId);
        productRepository.deleteById(productId);
        return product;
    }

    public User createUser(User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User email already exist");
        }
    }
}
