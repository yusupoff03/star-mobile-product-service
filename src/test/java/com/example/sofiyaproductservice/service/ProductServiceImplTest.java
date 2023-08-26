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
import org.springframework.data.domain.Example;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        String token="token";

        // Mocking productEntity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        productEntity.setUserId(userId);

        // Mocking inventoryDto
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setProductId(productId);

        // Mocking restTemplate exchange
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InventoryDto> entity = new HttpEntity<>(inventoryDto, httpHeaders);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        when(modelMapper.map(eq(productCreatDto), eq(ProductEntity.class))).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(restTemplate.exchange(any(URI.class), eq(HttpMethod.POST), eq(entity), eq(String.class)))
                .thenReturn(responseEntity);

        // Perform the test
        ProductEntity result = productService.add(productCreatDto, userId, amount,token);

        // Assertions
        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals(userId, result.getUserId());
        verify(modelMapper, times(1)).map(eq(productCreatDto), eq(ProductEntity.class));
        verify(productRepository, times(1)).save(any(ProductEntity.class));
        verify(restTemplate, times(1)).exchange(any(URI.class), eq(HttpMethod.POST), eq(entity), eq(String.class));
    }

    @Test
    public void testGetAllProducts() {
        // Mocking input data
        int size = 10;
        int page = 0;
        List<ProductEntity> products = Collections.singletonList(new ProductEntity());

        // Mocking productRepository findAll
        when(productRepository.findAll((Example<ProductEntity>) any())).thenReturn(products);

        // Perform the test
        List<ProductEntity> result = productService.getAllProducts(size, page);

        // Assertions
        assertNotNull(result);
        assertEquals(products, result);
        verify(productRepository, times(1)).findAll((Example<ProductEntity>) any());
    }
    @Test
    public void testDelete(){
        UUID productId= java.util.UUID.randomUUID();
        UUID userId=UUID.randomUUID();
        String token="token";
        Optional<ProductEntity> product=Optional.of(new ProductEntity());
        when(productRepository.findById(productId)).thenReturn(product);
    }

    // Add more test methods for other methods in the ProductServiceImpl class.
}

