package com.ecommerce.ms_pedido.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoDTO {

    @NotNull(message = "La fecha de compra es obligatoria")
    private LocalDate fechaCompra;

    @NotNull(message = "La cantidad de entradas es obligatoria")
    @Min(value = 1, message = "Debe comprar al menos una entrada")
    private Integer cantidadEntrada;

    @NotNull(message = "El total de la compra es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor que cero")
    private Double totalCompra;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;
}