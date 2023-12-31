package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.exception.UnauthorizedAccessException;
import com.example.sofiyaproductservice.service.product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductEntity> add(
            @RequestBody ProductCreatDto productCreatDto,
            @RequestParam UUID userId,
            @RequestParam Integer amount,
            HttpServletRequest request
    ){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Seller"))){
            throw new UnauthorizedAccessException("You don`t have permission to access this recourse");
        }
            return ResponseEntity.ok(productService.add(productCreatDto,userId,amount,request.getHeader("authorization")));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductEntity>> getAll(
            @RequestParam(required = false,defaultValue = "10") int size,
            @RequestParam(defaultValue = "0",required = false)  int page
    ){
        return ResponseEntity.ok(productService.getAllProducts(size, page));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> search(
            @RequestParam(defaultValue = "10",required = false) int size,
            @RequestParam(defaultValue = "0",required = false) int page,
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
            @RequestParam UUID productId,
            HttpServletRequest request
    ){
        return ResponseEntity.ok(productService.deleteById(productId,userId,request.getHeader("authorization")));
    }


}
