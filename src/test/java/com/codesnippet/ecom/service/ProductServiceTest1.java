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


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest1 {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    private static Product product=null;


    @BeforeAll
    public static void init()
    {
         product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("desc");
        product.setPrice(50);
        System.out.println("Before All");
    }

    @BeforeEach
    public void initEachTest()
    {
        System.out.println("Before Each");
    }


    @Test
    void addProductShouldAddProductSuccessFully() {
        System.out.println("addProductShouldAddProductSuccessFully");


        Mockito.when(productRepository.save(product)).thenReturn(product);
        Product addedProduct=productService.addProduct(product);
        Assertions.assertNotNull(addedProduct);
        Assertions.assertEquals(product.getId(), addedProduct.getId());
        Assertions.assertEquals(product.getName(), addedProduct.getName());
        Assertions.assertEquals(product.getDescription(), addedProduct.getDescription());
        Assertions.assertEquals(product.getPrice(), addedProduct.getPrice());
        Assertions.assertTrue(addedProduct.equals(product));
        Assertions.assertTrue(addedProduct.getId()==product.getId());
    }

    @Test
    void addProductShouldThrowExceptionForInvalidProductName() {
        System.out.println("addProductShouldThrowExceptionForInvalidProductName");
        product.setName("");


        RuntimeException runtimeException=Assertions.assertThrows(RuntimeException.class,()->{productService.addProduct(product);});
        Assertions.assertEquals(runtimeException.getMessage(),"Invalid Name Of Product");

        Mockito.verify(productRepository,Mockito.never()).save(Mockito.any(Product.class));


    }

    @Test
    void deleteProductShouldDeleteProductSuccessFully() {
        System.out.println("deleteProductShouldDeleteProductSuccessFully");
        Mockito.doNothing().when(productRepository).deleteById(1);
        productService.deleteProduct(1);
        Mockito.verify(productRepository,Mockito.times(1)).deleteById(1);
    }

    @Test
    void testPrivateMethod_ValidateProductName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("testPrivateMethod_ValidateProductName");
        Method validateProductName=ProductService.class.getDeclaredMethod("validateProductName",String.class);
        validateProductName.setAccessible(true);
        Boolean book=(boolean)validateProductName.invoke(productService,"Book");
        Assertions.assertTrue(book);

    }

    @Test
    void testPrivateMethod_ValidateProductNameIsInValid() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("testPrivateMethod_ValidateProductNameIsInValid");
        Method validateProductName=ProductService.class.getDeclaredMethod("validateProductName",String.class);
        validateProductName.setAccessible(true);
        Boolean book=(boolean)validateProductName.invoke(productService,"");
        Assertions.assertFalse(book);

    }


    @AfterAll
    public static void afterAll()
    {
        System.out.println("afterAll");
    }

    @AfterEach
    public void afterEach()
    {
        System.out.println("afterEach");
    }
}

