package com.Gemini1.Gemini1.repository;

import com.Gemini1.Gemini1.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {

}
