package com.example.sofiyaproductservice.service;

import com.example.sofiyaproductservice.domain.dto.InventoryDto;
import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.exception.DataNotFoundException;
import com.example.sofiyaproductservice.repository.product.ProductRepository;
import com.example.sofiyaproductservice.service.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductServiceImpl productService;

    private final UUID userId = UUID.randomUUID();
    private final String inventoryServiceUrl = "http://inventory-service/api/v1"; // replace with your actual URL

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, modelMapper, restTemplate);
        productService.add(inventoryServiceUrl);
    }

    @Test
    public void testAddProduct() {
        // Mock data
        ProductCreatDto productDto = new ProductCreatDto();
        productDto.setName("Test Product");
        Integer amount = 10;

        ProductEntity savedProduct = new ProductEntity();
        savedProduct.setId(UUID.randomUUID());
        savedProduct.setBrand(productDto.getName());
        savedProduct.setUserId(userId);

        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setAmount(amount);
        inventoryDto.setProductId(savedProduct.getId());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InventoryDto> entity = new HttpEntity<>(inventoryDto, httpHeaders);

        ResponseEntity<String> mockResponse = new ResponseEntity<>("Inventory updated", HttpStatus.OK);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(savedProduct);
        when(restTemplate.exchange(eq(inventoryServiceUrl + "/add"), eq(HttpMethod.POST), eq(entity), eq(String.class)))
                .thenReturn(mockResponse);

        // Call the method to add a product
        ProductEntity addedProduct = productService.add(productDto, userId, amount);

        // Verify the result
        assertNotNull(addedProduct);
        assertEquals(savedProduct.getId(), addedProduct.getId());
        assertEquals(savedProduct.getName(), addedProduct.getName());
        assertEquals(userId, addedProduct.getUserId());
    }

    @Test
    public void testGetAllProducts() {
        // Mock data
        int size = 5;
        int page = 0;
        List<ProductEntity> mockProducts = Arrays.asList(
                new ProductEntity(UUID.randomUUID(), "Product 1"),
                new ProductEntity(UUID.randomUUID(), "Product 2"),
                new ProductEntity(UUID.randomUUID(), "Product 3")
        );

        when(productRepository.findAll(any())).thenReturn(new ArrayList<>(mockProducts));

        // Call the method to get all products
        List<ProductEntity> result = productService.getAllProducts(size, page);

        // Verify the result
        assertNotNull(result);
        assertEquals(mockProducts.size(), result.size());
        assertEquals(mockProducts, result);
    }

    // Add more test cases for other methods in the ProductService if needed.
}

