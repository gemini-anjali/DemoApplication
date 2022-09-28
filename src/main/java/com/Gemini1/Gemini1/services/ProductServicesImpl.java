package com.Gemini1.Gemini1.services;

import com.Gemini1.Gemini1.entity.Category;
import com.Gemini1.Gemini1.entity.Products;
import com.Gemini1.Gemini1.exception.ResourceNotFoundException;
import com.Gemini1.Gemini1.repository.CategoryRepository;
import com.Gemini1.Gemini1.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServicesImpl implements ProductService {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Products> getProductList() {

        log.trace("Getting the category List");
        List <Products> productList= productsRepository.findAll();
        if(productList.size()==0)
        {
            log.error("No category List");
            throw new ResourceNotFoundException("No Data");
        }
        log.info("Getting all data for the category table");
        return productList;
    }

    @Override
    public Products updateProduct(Integer productId, Products products) {

        log.trace("In method update Product");
        log.info("Category Updated");
        Products updatedProduct = productsRepository.findById(productId)
                .orElseThrow(()->{
                            log.error("Category"+ productId +"does not exist");
                            return new ResourceNotFoundException("Category "+productId+" does not exist");
                        }
                );
        updatedProduct.setProductName(products.getProductName());
        updatedProduct.setProductDescription(products.getProductDescription());
        updatedProduct.setPrice(products.getPrice());
        return productsRepository.save(updatedProduct);
    }

    @Override
    public Products getProductById(Integer productId) {

        log.trace("In method get product by id");
        log.info("Getting Product By Id");
        return productsRepository.findById(productId)
                .orElseThrow(()->{
                            log.error("Category"+ productId +"does not exist");
                            return new ResourceNotFoundException("Category "+ productId+" does not exist");
                        }
                );
    }
    @Override
    public void deleteProduct(Integer productId) {

        log.trace("In method Delete Product");
        log.info("deleting product");
        productsRepository.DeleteProduct(productId);
    }

    @Override
    public Products addProduct(Integer categoryId, Products products){

        log.trace("In Method Add Product");
        log.info("dding Product");
        Optional<Category> category = categoryRepository.findById(categoryId);
        products.setCategory(category
                .orElseThrow(()->{
                            log.error("Category"+categoryId +"does not exist");
                            return new ResourceNotFoundException("Category "+categoryId+" does not exist");
                        }
                ));
        productsRepository.save(products);
        return products;
    }


}

