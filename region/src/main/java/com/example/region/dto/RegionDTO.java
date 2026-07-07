package com.example.region.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegionDTO {

    @NotNull(message = "El ID de la región es obligatorio")
    private Integer idRegion;

    @NotBlank(message = "El nombre de la región es obligatorio")
    @Size(min = 3, max = 80, message = "El nombre de la región debe tener entre 3 y 80 caracteres")
    private String nombreRegion;
}