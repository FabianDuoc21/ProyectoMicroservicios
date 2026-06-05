package com.ecommerce.ms_pago.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

//agregado
@Data
public class PagoDTO {

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que cero")
    private Double montoPago;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaPago;

    @NotNull(message = "El ID del pedido es obligatorio")
    private Long pedidoId;
}