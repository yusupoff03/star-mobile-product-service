package com.example.sofiyaproductservice.controller;


import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.service.product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() {
        // Mocking input data
        ProductCreatDto productCreatDto = new ProductCreatDto();
        UUID userId = UUID.randomUUID();
        Integer amount = 100;
        UUID productId = UUID.randomUUID();

        // Mocking productService.add
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        productEntity.setUserId(userId);
        when(productService.add(any(ProductCreatDto.class), any(UUID.class), anyInt(),anyString())).thenReturn(productEntity);

        // Perform the test

        // Assertions
    }

    @Test
    public void testGetAllProducts() {
        // Mocking input data
        int size = 10;
        int page = 0;
        List<ProductEntity> products = Collections.singletonList(new ProductEntity());

        // Mocking productService.getAllProducts
        when(productService.getAllProducts(anyInt(), anyInt())).thenReturn(products);

        // Perform the test
        ResponseEntity<List<ProductEntity>> response = productController.getAll(size, page);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productService, times(1)).getAllProducts(anyInt(), anyInt());
    }

    // Add more test methods for other endpoints in the ProductController class.
}

