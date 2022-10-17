package com.Gemini1.Gemini1.services;

import com.Gemini1.Gemini1.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     *
     * @return CategoryList
     */
    List<Category> getCategoryList();

    /**
     *
     * @param id categoryId to which the details are to updated
     * @param category updated category details
     * @return updatedCategory
     */
    Category updateCategory(Integer id, Category category);

    /**
     *
     * @param id categoryId which is to be fetched
     * @return category details for a particular categoryId
     */
    Category getCategoryById(Integer id);



    /**
     *
     * @param category category
     * @return category details which are added
     */
    Category addCategory(Category category);


    void deleteCategory(Integer categoryId);
}
