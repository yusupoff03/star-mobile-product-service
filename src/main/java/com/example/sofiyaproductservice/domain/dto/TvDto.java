package com.example.sofiyaproductservice.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TvDto {

    @NotEmpty(message = "isSmart cannot be empty")
    private Double isSmart;

    @NotEmpty(message = "Size cannot be empty")
    private Integer Size;

    @NotEmpty(message = "ScreenSpeed cannot be empty")
    private Integer ScreenSpeed;
}
