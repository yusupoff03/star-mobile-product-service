package com.example.sofiyaproductservice.service.phone;

import com.example.sofiyaproductservice.domain.dto.PhoneDto;
import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.PhoneEntity;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;

import java.util.UUID;

public interface PhoneService {
    PhoneEntity add(PhoneDto phoneDto, UUID userId, Integer amount);

}
