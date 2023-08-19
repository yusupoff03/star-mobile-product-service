package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.service.product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('Seller')")
    public ResponseEntity<ProductEntity> add(
            @RequestBody ProductCreatDto productCreatDto,
            @RequestParam UUID userId,
            @RequestParam Integer amount,
            HttpServletRequest request
    ){

        return ResponseEntity.ok(productService.add(productCreatDto,userId,amount,request.getHeader("authorization")));
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
    @PreAuthorize("hasRole('Seller')")
    public ResponseEntity<ProductEntity> update(
            @RequestBody ProductCreatDto productCreatDto,
            @PathVariable UUID userId,
            @RequestParam UUID productId
            ){
        return ResponseEntity.ok(productService.update(productCreatDto,productId,userId));
    }

    @DeleteMapping("/{userId}/delete")
    @PreAuthorize("hasRole('Seller')")
    public ResponseEntity<Boolean> delete(
            @PathVariable UUID userId,
            @RequestParam UUID productId
    ){
        return ResponseEntity.ok(productService.deleteById(productId,userId));
    }




}
