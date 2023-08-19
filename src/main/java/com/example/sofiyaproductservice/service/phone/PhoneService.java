package com.example.sofiyaproductservice.service.phone;

import com.example.sofiyaproductservice.domain.dto.PhoneDto;

import com.example.sofiyaproductservice.domain.entity.PhoneEntity;


import java.util.List;
import java.util.UUID;

public interface PhoneService {
    PhoneEntity add(PhoneDto phoneDto, UUID userId, Integer amount,String token);

    List<PhoneEntity> getAllPhone(int size, int page);
    List<PhoneEntity> search(int page,int size,String name);
    Boolean deleteById(UUID id,UUID userId,String token);
    PhoneEntity update(PhoneDto update,UUID id,UUID userId);


}
