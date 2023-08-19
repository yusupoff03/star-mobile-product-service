package com.example.sofiyaproductservice.service.tv;

import com.example.sofiyaproductservice.domain.dto.InventoryDto;

import com.example.sofiyaproductservice.domain.dto.TvDto;

import com.example.sofiyaproductservice.domain.entity.TvEntity;
import com.example.sofiyaproductservice.exception.DataNotFoundException;
import com.example.sofiyaproductservice.repository.tv.TvRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service

public class TvServiceImpl implements TvService {

    private final TvRepository tvRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;


    @Value("${services.inventory-url}")
    private String inventoryServiceUrl;

    @Override
    public TvEntity add(TvDto tvDto, UUID userId, Integer amount, String token) {
        TvEntity tvEntity = modelMapper.map(tvDto, TvEntity.class);
        tvEntity.setUserId(userId);
        TvEntity save = tvRepository.save(tvEntity);
        InventoryDto inventoryDto = InventoryDto.builder().productCount(amount).productId(save.getId()).build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InventoryDto> entity = new HttpEntity<>(inventoryDto, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URI.create(inventoryServiceUrl + "/add"),
                HttpMethod.POST, entity, String.class);
        return save;
    }

    @Override
    public List<TvEntity> getAllTvs(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        List<TvEntity> content = tvRepository.findAll(pageable).getContent();
        if (content.isEmpty()) {
            throw new DataNotFoundException("Products not found");
        }
        return content;
    }

    @Override
    public List<TvEntity> search(int page, int size, String name) {

        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        return tvRepository.searchTvEntitiesByModelContainingIgnoreCase(name, pageable);
    }

    @Override
    public Boolean deleteById(UUID id, UUID userId) {
        TvEntity tvEntityNotFound = tvRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Tv not found"));
        if (tvEntityNotFound.getUserId().equals(userId)) {
            tvRepository.deleteById(id);
            return true;

        }
        throw new DataNotFoundException("Tv not found");
    }

    @Override
    public TvEntity update(TvDto update, UUID tvId, UUID userId) {
        TvEntity tvEntity = tvRepository.findById(tvId)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
        if (tvEntity.getUserId().equals(userId)) {
            modelMapper.map(update, tvEntity);
            return tvRepository.save(tvEntity);
        }
        throw new DataNotFoundException("Product not found");
    }


    public void addInventory(TvEntity save, Integer amount, String token) {
        InventoryDto inventoryDto = new InventoryDto(save.getId(), amount);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        token = token.substring(7);
        httpHeaders.setBearerAuth(token);
        HttpEntity<InventoryDto> entity = new HttpEntity<>(inventoryDto, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URI.create(inventoryServiceUrl + "/add"),
                HttpMethod.POST, entity, String.class);
        String body = exchange.getBody();
    }


}


