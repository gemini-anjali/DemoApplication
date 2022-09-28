package com.Gemini1.Gemini1.services;

import com.Gemini1.Gemini1.entity.Category;
import com.Gemini1.Gemini1.exception.ResourceNotFoundException;
import com.Gemini1.Gemini1.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServicesImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryrepository;

    @Override
    public List<Category> getCategoryList() {
        log.trace("Getting the category List");
        List <Category> categoryList= categoryrepository.findAll();
        if(categoryList.size()==0)
        {
            log.error("No category List");
            throw new ResourceNotFoundException("No Data");
        }
        log.info("Getting all data for the category table");
        return categoryList;
    }

    @Override
    public Category updateCategory(Integer id, Category category) {

        log.trace("In the method update Category");
        Category updateCategory = categoryrepository.findById(id)
                .orElseThrow(()->{
                    log.error("Category"+id +"does not exist");
                    return new ResourceNotFoundException("Category "+id+" does not exist");
                }
                );
        updateCategory.setCategoryName(category.getCategoryName());
        updateCategory.setCategoryDescription(category.getCategoryDescription());
        return categoryrepository.save(updateCategory);
    }

    @Override
    public Category addCategory(Category category)
    {
        log.trace("In method AddCategory");
        log.info("Category Added Successfully");
        return categoryrepository.save(category);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {

        log.trace("In method Get Category By Id");
        log.info("Getting Category By Id");
        return categoryrepository.findById(categoryId)
                .orElseThrow(()->{
                            log.error("Category"+ categoryId +"does not exist");
                            return new ResourceNotFoundException("Category "+ categoryId+" does not exist");
                        }
                );
    }


    @Override
    public void deleteCategory(Integer categoryId) {

        log.trace("In method Delete Category");
        log.info("Delete Category");
        categoryrepository.DeleteCategory(categoryId);
    }
}
