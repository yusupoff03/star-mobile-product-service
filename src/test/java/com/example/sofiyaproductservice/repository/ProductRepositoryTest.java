package com.example.sofiyaproductservice.repository;


import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductRepositoryImpl productRepositoryImpl;

    @Test
    public void testFindByName() {
        // Mock data
        String productName = "Test Product";
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(UUID.randomUUID());
        productEntity.setName(productName);
        when(productRepository.findByName(productName)).thenReturn(Optional.of(productEntity));

        // Call the method in the implementation class
        Optional<ProductEntity> result = productRepositoryImpl.findByName(productName);

        // Verify the result
        assertEquals(productEntity, result.orElse(null));
    }

    @Test
    public void testSearchProductEntitiesByNameContainingIgnoreCase() {
        // Mock data
        String searchTerm = "test";
        List<ProductEntity> mockProducts = Arrays.asList(
                new ProductEntity(UUID.randomUUID(), "Test Product 1"),
                new ProductEntity(UUID.randomUUID(), "Another Test Product"),
                new ProductEntity(UUID.randomUUID(), "Yet Another Test")
        );
        Page<ProductEntity> mockProductPage = new PageImpl<>(mockProducts);
        when(productRepository.searchProductEntitiesByNameContainingIgnoreCase(eq(searchTerm), any(Pageable.class)))
                .thenReturn(mockProductPage);

        // Call the method in the implementation class
        Page<ProductEntity> result = productRepositoryImpl.searchProductEntitiesByNameContainingIgnoreCase(searchTerm, Pageable.unpaged());

        // Verify the result
        assertEquals(mockProductPage, result);
    }

    // Add more test cases for other methods in the ProductRepository if needed.
}

