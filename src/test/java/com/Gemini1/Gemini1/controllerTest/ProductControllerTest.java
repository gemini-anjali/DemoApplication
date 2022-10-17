package com.Gemini1.Gemini1.controllerTest;

import com.Gemini1.Gemini1.controller.ProductController;
import com.Gemini1.Gemini1.entity.Products;
import com.Gemini1.Gemini1.exception.ResourceNotFoundException;
import com.Gemini1.Gemini1.services.ProductService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {ProductControllerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.project1.project")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc ;

    @Mock
    ProductService productService;


    @InjectMocks
    ProductController productController;
    List<Products> products;
    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    @Order(1)
    public void testGetProductList() throws Exception {
        products = new ArrayList<>();
        products.add(new Products(1,
                "name",
                "description",
                4500,
                false,
                false,
                null,
                null,
                null));
        when(productService.getProductList()).thenReturn(products);
        this.mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(2)
    void testGetProductListResourceNotFound() throws Exception
    {
        Integer productId=1;

        when(this.productService.getProductList()).thenThrow( ResourceNotFoundException.class);

        this.mockMvc.perform(get("/product",productId))
                .andExpect(status().isNotFound());


    }

    @Test
    @Order(3)
    void testGetProductById() throws Exception {

        Integer productId=1;
        Products pod1 =new Products(1,
                "name",
                "description",
                4500,
                false,
                false,
                null,
                null,
                null);
        when(this.productService.getProductById(productId)).thenReturn(pod1);
        this.mockMvc.perform(get("/product/{Id}",productId))
                .andExpect(MockMvcResultMatchers.jsonPath(".productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".productName").value("name"))
                .andExpect(MockMvcResultMatchers.jsonPath(".productDescription").value("description"))
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test
    @Order(4)
    void getProductByIdResourceNotFound() throws Exception
    {
        Integer productId=1;

        when(this.productService.getProductById(productId)).thenThrow( ResourceNotFoundException.class);

        this.mockMvc.perform(get("/product/{Id}",productId))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void testAddProduct() throws Exception {

        Integer categoryId=1;
        Products product =new Products(1,
                "name",
                "description",
                4500,
                false,
                false,
                null,
                null,
                null);

        when(productService.addProduct(anyInt(),any(Products.class))).thenReturn(product);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(product);
        this.mockMvc.perform(post("/category/{Id}/products",categoryId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void testUpdateCategory() throws Exception {
        Integer CategoryId=1;
        Products product =new Products(1,
                "name",
                "description",
                4500,
                false,
                false,
                null,
                null,
                null);
        int productId = 1;
        when(productService.getProductById(productId)).thenReturn(product);
        when(productService.updateProduct(productId,product)).thenReturn(product);
        when(productService.addProduct(CategoryId,product)).thenReturn(product);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(product);
        this.mockMvc.perform(put("/product/{Id}",productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))

                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @Order(7)
    void updateCategoryResourceNotFound() throws Exception {


        Products product =new Products(1,
                "name",
                "description",
                4500,
                false,
                false,
                null,
                null,
                null);
        int productId = 1;

        when(productService.updateProduct(anyInt(),any(Products.class))).thenThrow(ResourceNotFoundException.class);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(product);
        this.mockMvc.perform(put("/product/{Id}",productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isNotFound());

    }

    @Test
    @Order(8)
    public void testDeleteProduct() throws Exception {
        Integer productId = 1;
        productService.deleteProduct(productId);

        this.mockMvc.perform(delete("/product/{productId}", productId))
                .andExpect(status().isOk())
                .andDo(print());
    }


}

