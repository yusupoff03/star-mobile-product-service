package com.example.sofiyaproductservice.service.laptop;

import com.example.sofiyaproductservice.domain.dto.LaptopDto;
import com.example.sofiyaproductservice.domain.entity.LaptopEntity;

import java.util.List;
import java.util.UUID;

public interface LaptopService {
    LaptopEntity add(LaptopDto laptop, UUID userId, Integer amount);
    List<LaptopEntity> getAllLaptops(int size, int page);
    List<LaptopEntity> search(int page,int size,String name);
    Boolean deleteById(UUID laptopId, UUID userId, String token);
    LaptopEntity update(LaptopDto update,UUID id,UUID userId);
}
