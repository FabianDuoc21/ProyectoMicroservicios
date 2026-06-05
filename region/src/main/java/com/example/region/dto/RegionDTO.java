package com.example.region.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {

    @NotNull(message = "El ID es obligatorio")
    private Integer idRegion;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombreRegion;
}
