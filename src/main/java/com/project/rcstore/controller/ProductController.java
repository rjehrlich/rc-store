package com.project.rcstore.controller;

import com.project.rcstore.model.Product;
import com.project.rcstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * getProducts calls the getProduct method in the productService and
     * @return a list object of products from db
     */
    @GetMapping(path = "/products/")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    /**
     * getProduct calls the getProduct method in the productService passing in
     * @param productId
     * @return a single product by that id
     */
    @GetMapping(path = "/products/{productId}")
    public Optional<Product> getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }


    /**
     * Create product method takes in product object and checks if name already exists. If not return new object saved.
     * @param productObject
     * @return
     */
    @PostMapping(path = "/products/")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product productObject) {
        return productService.createProduct(productObject);
    }

    /**
     * updateProduct method calls updateProduct method from the productService with
     * @param productId
     * @param productObject and
     * @return to the user the updated product information
     */
    @PutMapping(path = "/products/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product productObject) {
        return productService.updateProduct(productId, productObject);
    }

    /**
     * deleteProduct method calls the deleteProduct method in the productService with
     * @param productId and
     * @returns to the user the deleted product by id
     */
    @DeleteMapping(path = "/products/{productId}")
    public Optional<Product> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

}
