package com.Gemini1.Gemini1.serviceTest;

import com.Gemini1.Gemini1.entity.Category;
import com.Gemini1.Gemini1.exception.ResourceNotFoundException;
import com.Gemini1.Gemini1.repository.CategoryRepository;
import com.Gemini1.Gemini1.services.CategoryServicesImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest(classes={CategoryServiceTest.class})
public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServicesImpl categoryService;

    @Test
    @Order(1)
    public void testGetCategoryList()

    {
        List <Category> myCategory= new ArrayList<>();
        myCategory.add(new Category(1,
                "category1",
                "category1is",
                true,
                false,
                null,
                null));
        myCategory.add(new Category(2,
                "category1",
                "category1is",
                true,
                false,
                null,
                null));
        when(categoryRepository.findAll()).thenReturn(myCategory);
        categoryService.getCategoryList();
        assertEquals(2,categoryService.getCategoryList().size());
    }

    @Test
    @Order(2)
    void testGetCategoryListResourceNotFoundException() {

        List <Category> myCategory= new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(myCategory);

        assertThatThrownBy(() -> categoryService.getCategoryList())
                .isInstanceOf(ResourceNotFoundException.class);

    }

    @Test
    @Order(3)
    public void testGetCategoryById() {

        Category category = new Category(1,
                "category1",
                "description",
                true,
                false,
                null,
                null);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        assertEquals("category1",this.categoryService.getCategoryById(1).getCategoryName());
        assertEquals("description",this.categoryService.getCategoryById(1).getCategoryDescription());

      }



    @Test
    @Order(4)
    void testGetCategoryIdResourceNotFoundException() {
        Integer id = 1;

        when(categoryRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.getCategoryById(id))
                .isInstanceOf(ResourceNotFoundException.class);

    }

    @Test
    @Order(5)
    public void testAddCategory()
    {
        Category category= new Category(3,
                "category3",
                "category1is",
                true,
                false,
                null,
                null);
        when(categoryRepository.save(category)).thenReturn(category);
        categoryService.addCategory(category);
        assertEquals(category,categoryService.addCategory(category));
    }


   @Test
   @Order(6)
    void testUpdateCategory() {
        Category category =new Category(1,
                "name",
                "category1is",
                false,
                false,
                null,
                null);
       when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
       when(categoryRepository.save(any())).thenReturn(category);
       Category category1 = categoryService.updateCategory(1,category);
       assertEquals(category.getCategoryName(), category1.getCategoryName());
    }


    @Test
    @Order(7)
    public void testUpdateCategoryResourceNotFoundException() {
        int id = 1;
        Category category =new Category(1,
                null,
                "category1is",
                false,
                false,
                null,
                null);

        when(categoryRepository.findById( id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.updateCategory(id,category))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @Order(8)
    public void testDeleteCategory(){

        int categoryId =1 ;
        Category category = new Category(1,
                "category1",
                "category1is",
                true,
                false,
                null,
                null);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        categoryService.deleteCategory(categoryId);
        assertTrue(category.isDeleted());
        assertFalse(category.isActive());

    }



}

