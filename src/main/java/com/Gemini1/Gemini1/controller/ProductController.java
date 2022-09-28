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
     * Get Product */

    @GetMapping("/product")
    public ResponseEntity<List<Products>> getProductList() {

            log.info("Getting the list of all product");
            List<Products> productList = productService.getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);

    }


    /**
     * Add Product */

    @PostMapping("category/{Id}/products")
    public ResponseEntity<Object> addProduct(@PathVariable Integer Id, @Valid @RequestBody Products newProduct) {

            log.info("POST Request for employee is successful");
            Products product = productService.addProduct(Id,newProduct);
            return new ResponseEntity<>(product, HttpStatus.CREATED);

    }

    /**
     * Get Product By id */

    @GetMapping("product/{Id}")
    public ResponseEntity<Products> getProductById(@PathVariable Integer Id) {

            log.info("Get Category By Id");
            Products product = this.productService.getProductById(Id);
            return new ResponseEntity<>(product, HttpStatus.OK);

    }


    /**
     * Update Product */

    @PutMapping("product/{Id}")
    public ResponseEntity<Products> updateProduct(@PathVariable("Id") Integer Id, @RequestBody Products category) {

            log.info("Update Category");
            productService.updateProduct(Id, category);
            return new ResponseEntity<>(productService.getProductById((Id)), HttpStatus.OK);

    }

    /**
     * Delete Product */

    @DeleteMapping("product/{Id}")
    public ResponseEntity<Products> deleteProduct(@PathVariable("Id") Integer Id) {

            log.info("category deleted");
            productService.deleteProduct(Id);
            return new ResponseEntity<>(HttpStatus.OK);

    }
}
