package com.example.evento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    @NotNull(message = "El ID es obligatorio")
    private Integer idEvento;

    @NotBlank(message = "El nombre del evento es obligatorio")
    private String nombreEvento;

    @NotBlank(message = "La fecha es obligatoria")
    private String fechaEvento;

    @NotNull(message = "El ID de la comuna es obligatorio")
    private Integer idComuna;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer idUsuario;
}