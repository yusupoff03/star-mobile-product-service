package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductEntity> add(
            @RequestBody ProductCreatDto productCreatDto
    ){
        return ResponseEntity.ok(productService.add(productCreatDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page
    ){
        return ResponseEntity.status(200).body(productService.getAllProducts(size, page));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> search(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam String name
    ){
        return ResponseEntity.status(200).body(productService.search(size, page, name));
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<ProductEntity> update(
            @RequestBody ProductCreatDto productCreatDto,
            @PathVariable UUID productId
            ){
        return ResponseEntity.ok(productService.update(productCreatDto,productId));
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity delete(
            @PathVariable UUID productId
    ){
        productService.deleteById(productId);
        return ResponseEntity.status(204).build();
    }




}
