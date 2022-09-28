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
     * Category Service */
    @Autowired
    private CategoryService categoryService;

    /**
     * Get All Category */

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategoryList() {

            log.info("Getting the list of all category");
            List<Category> categoryList = categoryService.getCategoryList();
            log.info("Get request for department is successful");
            return new ResponseEntity<>(categoryList, HttpStatus.OK);

    }

    /**
     * Add Category */

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@RequestBody Category addCategory) {

            log.info("category added");
            Category category = categoryService.addCategory(addCategory);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    /**
     * Get Category By CategoryId */

    @GetMapping("/category/{Id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer Id) {

            log.info("Getting the Category" + Id);
            Category category = categoryService.getCategoryById(Id);
            return new ResponseEntity<>(category,HttpStatus.OK);
    }

    /**
     * Update Category */

    @PutMapping("/category/{Id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("Id") Integer CategoryId, @RequestBody Category category) {

            log.info("Update Category");
            categoryService.updateCategory(CategoryId, category);
            return new ResponseEntity<>(categoryService.getCategoryById(CategoryId), HttpStatus.OK);
    }

    /**
     * Delete Category */

    @DeleteMapping("/category/{Id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("Id") Integer Id) {

            log.info("category deleted");
            categoryService.deleteCategory(Id);
            return new ResponseEntity<>( HttpStatus.OK);

    }
}
