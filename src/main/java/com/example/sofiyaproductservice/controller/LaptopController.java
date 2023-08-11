package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.LaptopDto;
import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.LaptopEntity;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.exception.RequestValidationException;
import com.example.sofiyaproductservice.service.laptop.LaptopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/laptop")
@RequiredArgsConstructor
public class LaptopController {

    private final LaptopService laptopService;
    @PostMapping("/{userId}/add")
    @PreAuthorize(value = "hasRole('CUSTOMER')")
    public ResponseEntity<LaptopEntity> add(
          @Valid @RequestBody LaptopDto laptopDto,
            @PathVariable UUID userId,
            @RequestParam Integer amount,
          BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(laptopService.add(laptopDto,userId,amount));
    }

}
