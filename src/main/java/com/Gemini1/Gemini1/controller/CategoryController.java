package com.Gemini1.Gemini1.controller;

import com.Gemini1.Gemini1.entity.Category;
import com.Gemini1.Gemini1.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class CategoryController {
    /**
     * Category Service
     */
    @Autowired
    private CategoryService categoryService;

    /**
     *
     * @return List <Category>
     */

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategoryList() {

            List<Category> categoryList = categoryService.getCategoryList();
            log.info("Get request for category is successful");
            return new ResponseEntity<>(categoryList, HttpStatus.OK);

    }

    /**
     *
     * @param addCategory category to be added
     * @return Category
     */

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@RequestBody Category addCategory) {

            Category category = categoryService.addCategory(addCategory);
            log.info("Post request for category is successful");
            return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    /**
     *
     * @param Id category id to be fetched
     * @return  Category
     */

    @GetMapping("/category/{Id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer Id) {

            Category category = categoryService.getCategoryById(Id);
            log.info("Get request for category is successful with Id" + Id);
            return new ResponseEntity<>(category,HttpStatus.OK);
    }

    /**
     *
     * @param CategoryId CategoryId to be changed
     * @param category new updated category
     * @return updated Category
     */

    @PutMapping("/category/{Id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("Id") Integer CategoryId, @RequestBody Category category) {

            categoryService.updateCategory(CategoryId, category);
            log.info("Put request for category is successful");
            return new ResponseEntity<>("Category details have been successfully updated", HttpStatus.OK);
    }

    /**
     *
     * @param categoryId categoryId which is to deleted
     * @return category successfully deleted
     */
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Integer categoryId) {

         categoryService.deleteCategory(categoryId);
         log.info("DELETE Request is successful for Category with id : "+ categoryId);
        return new ResponseEntity<>("category deleted successfully",HttpStatus.OK);
    }

}
