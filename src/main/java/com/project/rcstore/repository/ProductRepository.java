package com.project.rcstore.repository;

import com.project.rcstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductRepository extends JpaRepository in order to
 * inherit several methods for saving, deleting, and finding Movie entities
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // select name from Products where name =
    Product findByName(String name);

    // select brandName from Products where brandName =
    Product findByBrandName(String brandName);

    // get all products that belong to logged-in user
    List<Product> findByUserId(Long userId);

    // get product by id and userid
    Product findByIdAndUserId(Long productId, Long userId);

    // find product matches name and userid
    Product findByUserIdAndName(Long userId, String productName);
}
