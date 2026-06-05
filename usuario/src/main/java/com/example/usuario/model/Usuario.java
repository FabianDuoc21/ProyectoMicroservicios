package com.example.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @NotNull(message = "El ID es obligatorio")
    private Integer idUsuario;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(nullable = false, unique = true)
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;
}
