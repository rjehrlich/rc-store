package com.project.rcstore.seed;

import com.project.rcstore.model.Product;
import com.project.rcstore.model.User;
import com.project.rcstore.repository.ProductRepository;
import com.project.rcstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductDataLoader implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loadProductData();
    }

    private void loadProductData() {
        if (productRepository.count() == 0 || userRepository.count() == 0) {
            Product product1 = new Product(1L, "Metcon", 120.00, "Running Shoes", "Nike", "https://shorturl.at/bqMSV");
            Product product2 = new Product(2L, "EVA", 49.95, "Sandals", "Birkenstock", "https://shorturl.at/cdkS3");
            Product product3 = new Product(3L, "Sportswear Essentials", 35.00, "T-shirt", "Nike", "https://shorturl.at/CDV47");
            Product product4 = new Product(4L, "Fast Mid-Rise", 80.00, "Leggings", "Nike", "https://shorturl.at/hwL03");
            Product product5 = new Product(5L, "Adicolor Essentials", 60.00, "Sweatshirt", "Adidas", "https://tinyurl.com/4aubyubc");
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);

            User user1 = new User(1L, "Levi2", "levi@yahoo.com", "34jeans");
            User user2 = new User(2L, "John23", "john@gmail.com", "pass123");
            userRepository.save(user1);
            product1.setUser(user1);
            product4.setUser(user1);
            userRepository.save(user2);
            product2.setUser(user2);
            product3.setUser(user2);
            product5.setUser(user2);
        }
    }

}