package com.Gemini1.Gemini1.serviceTest;


import com.Gemini1.Gemini1.entity.Category;
import com.Gemini1.Gemini1.entity.Products;
import com.Gemini1.Gemini1.exception.ResourceNotFoundException;
import com.Gemini1.Gemini1.repository.CategoryRepository;
import com.Gemini1.Gemini1.repository.ProductsRepository;
import com.Gemini1.Gemini1.services.ProductServicesImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest(classes={ProductServiceTest.class})
public class ProductServiceTest {

    @Mock
    ProductsRepository productsRepository;
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ProductServicesImpl productServices;




    @Test

    public void test_getProductList()
    {   List<Products> myProduct= new ArrayList<>();
        myProduct.add(new Products(1,
                "category1",
                "category1is",
                100,
                false,
                false,
                null,
                null,
                null));
        myProduct.add(new Products(2,
                "category1",
                "category1is",
                100,
                false,
                false,
                null,
                null,
                null));

        when(productsRepository.findAll()).thenReturn(myProduct);
        productServices.getProductList();
        assertEquals(2,productServices.getProductList().size());
    }

    @Test
    void test_getCategoryListResourceNotFoundException() {

        List <Category> myCategory= new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(myCategory);

        assertThatThrownBy(() -> productServices.getProductList())
                .isInstanceOf(ResourceNotFoundException.class);

    }
    @Test

    public void test_addProduct()  {

        Products myProduct =new Products(1,
                "category1",
                "category1is",
                80,
                false,
                false,
                null,
                null,
                null);
        int categoryId = 1;
        Category category = new Category(1,"category1","description",true,false,null,null);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(productsRepository.save(myProduct)).thenReturn(myProduct);
        productServices.addProduct(categoryId, myProduct);
        assertEquals(category, myProduct.getCategory());
    }

    @Test
    public void test_getProductById()
    {

        Products product = new Products(1,
                "category1",
                "description",
                80,
                false,
                false,
                null,
                null,
                null);
        when(productsRepository.findById(1)).thenReturn(Optional.of(product));
        assertEquals("category1",this.productServices.getProductById(1).getProductName());
        assertEquals("description",this.productServices.getProductById(1).getProductDescription());
    }

    @Test
    void test_getProductByIdResourceNotFoundException() {
        Integer id = 1;

        when(productsRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> productServices.getProductById(id))
                .isInstanceOf(ResourceNotFoundException.class);

    }


    @Test
    void test_updateProduct() {
        Products product =new Products(1,
                "name",
                "category1is",
                4500,
                false,
                false,
                null,
                null,
                null);
        when(productsRepository.findById(any())).thenReturn(Optional.of(product));
        when(productsRepository.save(any())).thenReturn(product);
        Products product1 = productServices.updateProduct(1,product);
        assertEquals(product.getProductName(), product1.getProductName());
    }

    @Test
    public void test_updateCategoryResourceNotFoundException() {
        int id = 1;
        Products product = new Products(1,
                "category1",
                "category1is",
                80,
                false,
                false,
                null,
                null,
                null);

        when(categoryRepository.findById( id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> productServices.updateProduct(id,product))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void test_deleteProduct(){
        List <Products> myProduct= new ArrayList<>();
        myProduct.add(new Products(1,"category1","category1is",50000,false,false,null,null,null));
        int productId =1;
        productsRepository.DeleteProduct(productId);
        when(productsRepository.findAll()).thenReturn(myProduct);
        assertThat(myProduct).isNotNull();
    }


}
