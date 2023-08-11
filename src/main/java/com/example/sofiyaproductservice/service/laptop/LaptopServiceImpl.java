package com.example.sofiyaproductservice.service.laptop;

import com.example.sofiyaproductservice.domain.dto.InventoryDto;
import com.example.sofiyaproductservice.domain.dto.LaptopDto;
import com.example.sofiyaproductservice.domain.entity.LaptopEntity;
import com.example.sofiyaproductservice.repository.laptop.LaptopRepository;
import com.example.sofiyaproductservice.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class LaptopServiceImpl implements LaptopService{
    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Value("${services.inventory-url}")
    private String inventoryServiceUrl;
    @Override
    public LaptopEntity add(LaptopDto laptop, UUID userId, Integer amount) {
        LaptopEntity laptopEntity = modelMapper.map(laptop, LaptopEntity.class);
        laptopEntity.setUserId(userId);
        LaptopEntity saved = laptopRepository.save(laptopEntity);

        InventoryDto inventoryDto = InventoryDto.builder().amount(amount).productId(saved.getId()).build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InventoryDto> inventoryDtoHttpEntity = new HttpEntity<>(inventoryDto, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                URI.create(inventoryServiceUrl + "/add"),
                HttpMethod.POST,
                inventoryDtoHttpEntity,
                String.class
        );

        return saved;
    }


    @Override
    public List<LaptopEntity> getAllLaptops(int size, int page) {
        return null;
    }

    @Override
    public List<LaptopEntity> search(int page, int size, String name) {
        return null;
    }

    @Override
    public void deleteById(UUID id, UUID userId) {

    }

    @Override
    public LaptopEntity update(LaptopDto update, UUID id, UUID userId) {
        return null;
    }
}
