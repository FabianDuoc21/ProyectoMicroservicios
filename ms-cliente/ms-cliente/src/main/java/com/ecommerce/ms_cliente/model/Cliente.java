package com.ecommerce.ms_cliente.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCliente;

    @Column(nullable = false, unique = true)
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correoCliente;

    @Column(nullable = false)
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefonoCliente;
}