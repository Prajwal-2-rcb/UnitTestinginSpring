package com.codesnippet.ecom.service;

import com.codesnippet.ecom.Entity.Product;
import com.codesnippet.ecom.Repository.ProductRepository;
import com.codesnippet.ecom.Service.ProductService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

    private static Product product=null;

    @BeforeAll
    public static void init()
    {
        System.out.println("BeforeAll");
        product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("description");
        product.setPrice(200);
    }
    @BeforeEach
    public void initEachTest()
    {
        System.out.println("BeforeEachTest");
    }
    @Test
    void addProductShouldAddProductSuccessfully()
    {

//        Product product = new Product();
//        product.setId(1);
//        product.setName("test");
//        product.setDescription("description");
//        product.setPrice(200);
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

    @Test
    void addProductShouldThrowExceptionForInvalidProductName()
    {
//
//        Product product = new Product();
//        product.setId(1);
//        product.setName("");
//        product.setDescription("description");
//        product.setPrice(200);

        product.setName("");

        RuntimeException runtimeException=assertThrows(RuntimeException.class, ()->{
            productService.addProduct(product);
        });

        assertEquals("Invalid Name Of Product",runtimeException.getMessage());
        verify(productRepository,never()).save(any(Product.class));

    }

    @Test
    public void deleteProductShouldDeleteProductSuccessfully()
    {
        doNothing().when(productRepository).deleteById(1);
        productService.deleteProduct(1);
//        Mockito.verify(productRepository,times(1)).deleteById(1);
       verify(productRepository,times(1)).deleteById(1);
    }

    @Test
    void testPrivateMethod_validateProductName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateProductName=ProductService.class.getDeclaredMethod("validateProductName", String.class);
        validateProductName.setAccessible(true);
        Boolean book= (Boolean) validateProductName.invoke(productService,"Book");
        assertTrue(book);
    }

    @Test
    void testPrivateMethod_validateProductNameIsInValid() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateProductName=ProductService.class.getDeclaredMethod("validateProductName", String.class);
        validateProductName.setAccessible(true);
        Boolean book= (Boolean) validateProductName.invoke(productService,"");
        assertFalse(book);
    }


    @AfterAll
    public static void Destory(){
        System.out.println("AfterAll");
    }

    @AfterEach
    public void cleanUp()
    {
        System.out.println("AfterEachTest");
    }

}
