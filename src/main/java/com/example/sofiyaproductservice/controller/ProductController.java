package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/{userId}/add")
    @PreAuthorize(value = "hasRole='CUSTOMER'")
    public ResponseEntity<ProductEntity> add(
            @RequestBody ProductCreatDto productCreatDto,
            @PathVariable UUID userId,
            @RequestParam Integer amount
    ){
        return ResponseEntity.ok(productService.add(productCreatDto,userId,amount));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page
    ){
        return ResponseEntity.ok(productService.getAllProducts(size, page));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> search(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam String name
    ){
        return ResponseEntity.status(200).body(productService.search(size, page, name));
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<ProductEntity> update(
            @RequestBody ProductCreatDto productCreatDto,
            @PathVariable UUID userId,
            @RequestParam UUID productId
            ){
        return ResponseEntity.ok(productService.update(productCreatDto,productId,userId));
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<Boolean> delete(
            @PathVariable UUID userId,
            @RequestParam UUID productId
    ){
        return ResponseEntity.ok(productService.deleteById(productId,userId));
    }




}
