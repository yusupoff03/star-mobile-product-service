package com.example.sofiyaproductservice.controller;

import com.example.sofiyaproductservice.domain.dto.TvDto;

import com.example.sofiyaproductservice.domain.entity.TvEntity;
import com.example.sofiyaproductservice.service.tv.TvService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product/tv")
@RequiredArgsConstructor
public class TvController {
    private final TvService tvService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('Seller')")
    public ResponseEntity<TvEntity> add(
            @RequestBody TvDto tvDto,
            @RequestParam UUID userId,
            @RequestParam Integer amount,
            HttpServletRequest request
    ) {

        return ResponseEntity.ok(tvService.add(tvDto, userId, amount, request.getHeader("authorization")));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TvEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page
    ) {
        return ResponseEntity.ok(tvService.getAllTvs(size, page));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TvEntity>> search(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam String name
    ) {
        return ResponseEntity.status(200).body(tvService.search(size, page, name));
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<TvEntity> update(
            @RequestBody TvDto tvDto,
            @PathVariable UUID userId,
            @RequestParam UUID tvId
    ) {
        return ResponseEntity.ok(tvService.update(tvDto, tvId, userId));
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<Boolean> delete(
            @PathVariable UUID userId,
            @RequestParam UUID tvId
    ) {
        return ResponseEntity.ok(tvService.deleteById(tvId, userId));
    }


}
