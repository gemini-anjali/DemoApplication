package com.Gemini1.Gemini1.services;

import com.Gemini1.Gemini1.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();

    Category updateCategory(Integer id, Category category);

    Category getCategoryById(Integer id);

    void deleteCategory(Integer id);

    Category addCategory(Category category);


}
