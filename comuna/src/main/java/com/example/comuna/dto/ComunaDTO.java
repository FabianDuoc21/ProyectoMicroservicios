package com.example.comuna.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComunaDTO {

    @NotNull(message = "El ID es obligatorio")
    private Integer idComuna;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombreComuna;

    @NotNull(message = "El ID de la región es obligatorio")
    private Integer idRegion;
}
