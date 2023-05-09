package com.project.rcstore.seed;

import com.project.rcstore.model.Product;
import com.project.rcstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductDataLoader implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (productRepository.count() == 0) {
            Product product1 = new Product(1L, "Metcon", 120.00, "Running Shoes", "Nike", "https://shorturl.at/bqMSV");
            Product product2 = new Product(2L, "EVA", 49.95, "Sandals", "Birkenstock", "https://shorturl.at/cdkS3");
            Product product3 = new  Product(3L, "Sportswear Essentials",35.00, "T-shirt", "Nike", "https://shorturl.at/CDV47");
            Product product4 = new Product(4L, "Fast Mid-Rise",80.00, "Leggings", "Nike", "https://shorturl.at/hwL03");
            Product product5 = new  Product(5L, "Adicolor Essentials",60.00, "Sweatshirt", "Adidas", "https://tinyurl.com/4aubyubc");
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
        }
    }
}
