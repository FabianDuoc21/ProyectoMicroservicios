package com.example.comuna.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comunas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comuna {

    @Id
    @NotNull(message = "El ID es obligatorio")
    private Integer idComuna;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreComuna;

    @Column(nullable = false)
    @NotNull(message = "El ID de la región es obligatorio")
    private Integer idRegion;
}