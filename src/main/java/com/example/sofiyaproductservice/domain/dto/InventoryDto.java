package com.example.sofiyaproductservice.domain.dto;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryDto {
    private UUID productId;
    private Integer amount;
}
