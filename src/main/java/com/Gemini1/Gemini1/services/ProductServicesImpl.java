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

    /**
     *
     * @return Product List
     */
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

    /**
     *
     * @param productId productId for which the details are to be updated
     * @param products new product details
     * @return product with updated data
     */
    @Override
    public Products updateProduct(Integer productId, Products products) {

        log.trace("In method update Product");
        Products updatedProduct = productsRepository.findById(productId)
                .orElseThrow(()->{
                            log.error("Category"+ productId +"does not exist");
                            return new ResourceNotFoundException("Category "+productId+" does not exist");
                        }
                );
        updatedProduct.setProductName(products.getProductName());
        updatedProduct.setProductDescription(products.getProductDescription());
        updatedProduct.setPrice(products.getPrice());
        productsRepository.save(updatedProduct);
        return updatedProduct;
    }

    /**
     *
     * @param productId productId which is to searched
     * @return product details for a productId
     */
    @Override
    public Products getProductById(Integer productId) {

        log.trace("In method get product by id");
        return productsRepository.findById(productId)
                .orElseThrow(()->{
                            log.error("Category"+ productId +"does not exist");
                            return new ResourceNotFoundException("Category "+ productId+" does not exist");
                        }
                );
    }


    /**
     *
     * @param categoryId categoryId to which the product is to be added
     * @param products products details which is to be added
     * @return productDetails which are added
     */
    @Override
    public Products addProduct(Integer categoryId, Products products){

        log.trace("In Method Add Product");
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

    /**
     *
     * @param productId productId which is to be deleted
     */
    @Override
    public void deleteProduct(Integer productId) {
        log.trace("Entering the method deleteProduct.");

        Products products = productsRepository.findById(productId).orElseThrow(
                () -> {
                    log.error("Category not found with id : "+ productId);
                    return new ResourceNotFoundException("Category not found with categoryId : "+ productId);
                }
        );

        products.setDeleted(true);
        products.setActive(false);
        productsRepository.save(products);
    }


}

