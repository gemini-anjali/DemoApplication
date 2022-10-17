package com.Gemini1.Gemini1.services;


import com.Gemini1.Gemini1.entity.Products;

import java.util.List;

public interface ProductService {

    /**
     *
     * @return product List for all the products
     */
    List<Products> getProductList();

    /**
     *
     * @param id productId for which the details are to be updated
     * @param products product details which are to be updated
     * @return product details which are updated
     */
    Products updateProduct(Integer id, Products products);

    /**
     *
     * @param id productId for the product details is to be fetched
     * @return product details for the particular product Id
     */
    Products getProductById(Integer id);


    /**
     *
     * @param categoryId categoryId in which the product is to be added
     * @param products products details which is to be added
     * @return product details which are added
     */
    Products addProduct(Integer categoryId, Products products) ;

    /**
     *
     * @param productId productId which is to be deleted
     */
    void deleteProduct(Integer productId);
}
