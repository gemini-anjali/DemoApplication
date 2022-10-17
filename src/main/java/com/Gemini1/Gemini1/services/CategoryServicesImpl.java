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

    /**
     * Get Category List */

    @Override
    public List<Category> getCategoryList() {
        log.trace("Getting the category List");
        List <Category> categoryList= categoryrepository.findAll();
        if(categoryList.size()==0)
        {
            log.error("No category List");
            throw new ResourceNotFoundException("No Data");
        }
        return categoryList;
    }


    /**
     *
     * @param id Category id to be updated
     * @param category Category details to be updated
     * @return Category with updated data
     */
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
        categoryrepository.save(updateCategory);
        return updateCategory;
    }


    /**
     *
     * @param category Category which is to be added
     * @return Category
     */
    @Override
    public Category addCategory(Category category)
    {
        log.trace("In method AddCategory");
        return categoryrepository.save(category);
    }


    /**
     *
     * @param categoryId categoryId which is to be searched
     * @return details of a particular CategoryId
     */
    @Override
    public Category getCategoryById(Integer categoryId) {

        log.trace("In method Get Category By Id");
        return categoryrepository.findById(categoryId)
                .orElseThrow(()->{
                            log.error("Category"+ categoryId +"does not exist");
                            return new ResourceNotFoundException("Category "+ categoryId+" does not exist");
                        }
                );
    }

    /**
     *
     * @param categoryId categoryId which is to be deleted
     */
    @Override
    public void deleteCategory(Integer categoryId) {
        log.trace("Entering the method deleteCategory.");

        Category category = categoryrepository.findById(categoryId).orElseThrow(
                () -> {
                    log.error("Category not found with id : "+ categoryId);
                    return new ResourceNotFoundException("Category not found with categoryId : "+ categoryId);
                }
        );

        category.setDeleted(true);
        category.setActive(false);
        categoryrepository.save(category);
    }

}
