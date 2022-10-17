package com.Gemini1.Gemini1.controllerTest;

import com.Gemini1.Gemini1.controller.CategoryController;
import com.Gemini1.Gemini1.entity.Category;
import com.Gemini1.Gemini1.exception.ResourceNotFoundException;
import com.Gemini1.Gemini1.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CategoryControllerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.Gemini1.Gemini1")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc ;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;
    List<Category> categories;
    Category category;
    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    @Order(1)
    public void testGetCategoryList() throws Exception {
        categories = new ArrayList<>();
        categories.add(new Category(1,
                "name",
                "description",
                false,
                false,
                null,
                null));
        when(categoryService.getCategoryList()).thenReturn(categories);
        this.mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(2)
    public void getCategoryListResourceNotFound() throws Exception
    {
        Integer categoryId=1;

        when(this.categoryService.getCategoryList()).thenThrow( ResourceNotFoundException.class);

        this.mockMvc.perform(get("/category",categoryId))
                .andExpect(status().isNotFound());


    }

    @Test
    @Order(3)
    public void testGetCategoryById() throws Exception
    {

        Integer categoryId=1;
        Category cat1 =new Category(1,
                "name",
                "description",
                false,
                false,
                null,
                null);
        when(this.categoryService.getCategoryById(categoryId)).thenReturn(cat1);
        this.mockMvc.perform(get("/category/{id}",categoryId))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryName").value("name"))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryDescription").value("description"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @Order(4)
    public void getCategoryByIdResourceNotFound() throws Exception
    {
        Integer categoryId=1;

        when(this.categoryService.getCategoryById(categoryId)).thenThrow( ResourceNotFoundException.class);

        this.mockMvc.perform(get("/category/{id}",categoryId))
                .andExpect(status().isNotFound());


    }

    @Test
    @Order(5)
    public void testAddCategory() throws Exception {
        category = new Category(1,
                "name",
                "description",
                true,
                false,
                null,
                null);

        when(categoryService.addCategory(category)).thenReturn(category);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(category);
        this.mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void testUpdateCategory() throws Exception {
        category = new Category(1,
                "name",
                "description",
                false,
                false,
                null,
                null);
        int categoryId = 1;
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        when(categoryService.updateCategory(categoryId,category)).thenReturn(category);
        when(categoryService.addCategory(category)).thenReturn(category);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(category);
        this.mockMvc.perform(put("/category/{Id}",categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))

                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @Order(7)
    public void testUpdateCategoryResourceNotFound() throws Exception {

        Integer categoryId=1;
        Category testCategory=new Category();
        testCategory.setCategoryId(1);
        testCategory.setCategoryName("some-name");
        testCategory.setCategoryDescription("some-description");

        when(categoryService.updateCategory(anyInt(),any(Category.class))).thenThrow(ResourceNotFoundException.class);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(category);
        this.mockMvc.perform(put("/category/{categoryId}",categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Order(8)
    public void testDeleteCategory() throws Exception {
        Integer categoryId = 1;
        categoryService.deleteCategory(categoryId);

        this.mockMvc.perform(delete("/category/{categoryId}", categoryId))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
