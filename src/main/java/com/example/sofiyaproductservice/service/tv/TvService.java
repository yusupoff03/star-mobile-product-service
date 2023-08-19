package com.example.sofiyaproductservice.service.tv;

import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.dto.TvDto;
import com.example.sofiyaproductservice.domain.entity.PhoneEntity;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.domain.entity.TvEntity;

import java.util.List;
import java.util.UUID;

public interface TvService {
    TvEntity add(TvDto tvDto, UUID userId, Integer amount,String token);
    List<TvEntity> getAllTvs(int size, int page);
    List<TvEntity> search(int page,int size,String name);
    Boolean deleteById(UUID id,UUID userId);
    TvEntity update( TvDto update, UUID id, UUID userId);
}
