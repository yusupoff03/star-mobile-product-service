package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.LaptopDto;
import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.LaptopEntity;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.exception.RequestValidationException;
import com.example.sofiyaproductservice.service.laptop.LaptopService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/product/laptop")
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
    @GetMapping("/get-all")
    public ResponseEntity<List<LaptopEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page
    ){
        return ResponseEntity.ok(laptopService.getAllLaptops(size, page));
    }

    @GetMapping("/search-by-name")
    public ResponseEntity<List<LaptopEntity>> search(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam String name
    ){
        return ResponseEntity.status(200).body(laptopService.search(size, page, name));
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<LaptopEntity> update(
            @Valid  @RequestBody LaptopDto laptopDto,
            @PathVariable UUID userId,
            @RequestParam UUID laptopId,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(laptopService.update(laptopDto,laptopId,userId));
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<Boolean> delete(
            @PathVariable UUID userId,
            @RequestParam UUID laptopId,
            HttpServletRequest request
    ){
        return ResponseEntity.ok(laptopService.deleteById(laptopId,userId,request.getHeader("authorization")));
    }


}
