package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.PhoneDto;
import com.example.sofiyaproductservice.domain.entity.PhoneEntity;
import com.example.sofiyaproductservice.service.phone.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/phone")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;


    @PostMapping("{userId}/add")
    @PreAuthorize(value = "hasRole('CUSTOMER')")
    public ResponseEntity<PhoneEntity> add(
            @RequestBody PhoneDto phoneDto,
            @PathVariable UUID userId,
            @RequestParam Integer amount
    ){
        return ResponseEntity.ok(phoneService.add(phoneDto,userId,amount));
    }

}
