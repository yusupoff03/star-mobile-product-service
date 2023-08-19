package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.PhoneDto;
import com.example.sofiyaproductservice.domain.entity.PhoneEntity;
import com.example.sofiyaproductservice.service.phone.PhoneService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product/phone")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;

    @PostMapping("{userId}/add")
    @PreAuthorize(value = "hasRole('CUSTOMER')")
    public ResponseEntity<PhoneEntity> add(
            @RequestBody PhoneDto phoneDto,
            @PathVariable UUID userId,
            @RequestParam Integer amount,
            HttpServletRequest request

    ){
        return ResponseEntity.ok(phoneService.add(phoneDto,userId,amount,request.getHeader("authorization")));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PhoneEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page
    ){
        return ResponseEntity.ok(phoneService.getAllPhone(size,page));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PhoneEntity>> search(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam String name
    ){
        return ResponseEntity.status(200).body(phoneService.search(size, page, name));
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<PhoneEntity> update(
            @RequestBody PhoneDto phoneDto,
            @PathVariable UUID userId,
            @RequestParam UUID phoneId
    ){
        return ResponseEntity.ok(phoneService.update(phoneDto,phoneId,userId));
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<Boolean> delete(
            @PathVariable UUID userId,
            @RequestParam UUID phoneId,
            HttpServletRequest request
    ){
        return ResponseEntity.ok(phoneService.deleteById(phoneId,userId,request.getHeader("authorization")));
    }









}
