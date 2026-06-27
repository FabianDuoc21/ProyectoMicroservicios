package com.ecommerce.ms_cliente.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCliente;

    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correoCliente;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefonoCliente;
}