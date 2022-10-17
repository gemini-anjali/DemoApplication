package com.Gemini1.Gemini1.repository;

import com.Gemini1.Gemini1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
