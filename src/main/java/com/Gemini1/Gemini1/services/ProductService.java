package com.Gemini1.Gemini1.services;


import com.Gemini1.Gemini1.entity.Products;

import java.util.List;

public interface ProductService {
    List<Products> getProductList();

    Products updateProduct(Integer id, Products products);

    Products getProductById(Integer id);

    void deleteProduct(Integer id);

    Products addProduct(Integer deptId, Products products) ;
}
