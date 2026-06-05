package com.example.region.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regiones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {

    @Id
    @NotNull(message = "El ID es obligatorio")
    private Integer idRegion;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreRegion;
}
