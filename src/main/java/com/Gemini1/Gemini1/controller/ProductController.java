package com.Gemini1.Gemini1.controller;


import com.Gemini1.Gemini1.entity.Products;
import com.Gemini1.Gemini1.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController

public class ProductController {

    /**
     * Product Service */
    @Autowired
    private ProductService productService;

    /**
     *
     * @return List<Product>
     */

    @GetMapping("/product")
    public ResponseEntity<List<Products>> getProductList() {

            List<Products> productList = productService.getProductList();
            log.info("Fetched the list of all product");
            return new ResponseEntity<>(productList, HttpStatus.OK);

    }


    /**
     *
     * @param Id category id in which product is added
     * @param newProduct newProduct details
     * @return newProduct
     */

    @PostMapping("category/{Id}/products")
    public ResponseEntity<Object> addProduct(@PathVariable Integer Id, @Valid @RequestBody Products newProduct) {

            Products product = productService.addProduct(Id,newProduct);
            log.info("POST Request for employee is successful");
            return new ResponseEntity<>(product, HttpStatus.CREATED);

    }

    /**
     *
     * @param Id productId
     * @return product details for the product id
     */

    @GetMapping("product/{Id}")
    public ResponseEntity<Products> getProductById(@PathVariable Integer Id) {

            Products product = this.productService.getProductById(Id);
            log.info("Product Fetched By Id");
            return new ResponseEntity<>(product, HttpStatus.OK);

    }


    /**
     *
     * @param Id product id for which the details are to be updated
     * @param product product details
     * @return products updated
     */

    @PutMapping("product/{Id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("Id") Integer Id, @RequestBody Products product) {

            productService.updateProduct(Id,product);
            log.info("Product Updated");
            return new ResponseEntity<>("Product details have been successfully  updated",HttpStatus.OK);

    }

    /**
     *
     * @param productId productId which is to be deleted
     * @return product
     */
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Integer productId) {

        productService.deleteProduct(productId);
        log.info("DELETE Request is successful for Category with id : "+ productId);
        return new ResponseEntity<>("product deleted successfully",HttpStatus.OK);
    }
}
