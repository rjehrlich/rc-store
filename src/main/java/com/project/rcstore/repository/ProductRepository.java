package com.project.rcstore.repository;

import com.project.rcstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** ProductRepository extends JpaRepository in order to
 *  inherit several methods for saving, deleting, and finding Movie entities
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // select name from Products where name =
    Product findByName(String name);

    // select brandName from Products where brandName =
    Product findByBrandName(String brandName);
}
