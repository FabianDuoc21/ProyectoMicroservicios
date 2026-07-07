package com.example.comuna.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ComunaDTO {

    @NotNull(message = "El ID de la comuna es obligatorio")
    private Integer idComuna;

    @NotBlank(message = "El nombre de la comuna es obligatorio")
    @Size(min = 3, max = 80, message = "El nombre de la comuna debe tener entre 3 y 80 caracteres")
    private String nombreComuna;

    @NotNull(message = "El ID de la región es obligatorio")
    private Integer idRegion;
}
