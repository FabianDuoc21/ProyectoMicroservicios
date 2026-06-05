package com.example.evento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eventos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @NotNull(message = "El ID es obligatorio")
    private Integer idEvento;

    @Column(nullable = false)
    @NotBlank(message = "El nombre del evento es obligatorio")
    private String nombreEvento;

    @Column(nullable = false)
    @NotBlank(message = "La fecha es obligatoria")
    private String fechaEvento;

    @Column(nullable = false)
    @NotNull(message = "El ID de la comuna es obligatorio")
    private Integer idComuna;

    @Column(nullable = false)
    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer idUsuario;
}
