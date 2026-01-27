package com.codesnippet.ecom.service;

import com.codesnippet.ecom.Entity.Product;
import com.codesnippet.ecom.Repository.ProductRepository;
import com.codesnippet.ecom.Service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;
    @Test
    void addProductShouldAddProductSuccessfully()
    {

        Product product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("description");
        product.setPrice(200);
//        Mockito.when(productRepository.save(product)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        Product addedProduct= productService.addProduct(product);
//        Assertions.assertNotNull(addedProduct);
//        Assertions.assertEquals(addedProduct.getId(), product.getId());
//        Assertions.assertEquals(addedProduct.getName(), product.getName());
//        Assertions.assertEquals(addedProduct.getDescription(), product.getDescription());
//        Assertions.assertEquals(addedProduct.getPrice(), product.getPrice());
//        Assertions.assertTrue(product.getId()==1);
        assertNotNull(addedProduct);
        assertEquals(addedProduct.getId(), product.getId());
        assertEquals(addedProduct.getName(), product.getName());
        assertEquals(addedProduct.getDescription(), product.getDescription());
        assertEquals(addedProduct.getPrice(), product.getPrice());
        assertTrue(product.getId()==1);

    }
}
