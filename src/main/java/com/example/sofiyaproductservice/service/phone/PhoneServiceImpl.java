package com.example.sofiyaproductservice.service.phone;

import com.example.sofiyaproductservice.domain.dto.InventoryDto;
import com.example.sofiyaproductservice.domain.dto.PhoneDto;
import com.example.sofiyaproductservice.domain.entity.PhoneEntity;
import com.example.sofiyaproductservice.repository.product.PhoneRepository;
import com.example.sofiyaproductservice.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    private final PhoneRepository phoneRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;


    @Value("${services.inventory-url}")
    private String inventoryServiceUrl;

    @Override
    public PhoneEntity add(PhoneDto phoneDto, UUID userId, Integer amount) {
        PhoneEntity phoneEntity = modelMapper.map(phoneDto,PhoneEntity.class);
        phoneEntity.setUserId(userId);
        PhoneEntity save = phoneRepository.save(phoneEntity);
        InventoryDto inventoryDto = InventoryDto.builder().amount(amount).productId(save.getId()).build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InventoryDto> entity=new HttpEntity<>(inventoryDto,httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URI.create(inventoryServiceUrl + "/add"),
                HttpMethod.POST, entity, String.class);
        return save;
    }
}
