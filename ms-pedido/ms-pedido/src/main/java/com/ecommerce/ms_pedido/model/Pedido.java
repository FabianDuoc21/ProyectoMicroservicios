package com.ecommerce.ms_pedido.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @NotNull(message = "La fecha de compra es obligatoria")
    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "Debe comprar al menos una entrada")
    @Column(name = "cantidad_entrada", nullable = false)
    private Integer cantidadEntrada;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor que cero")
    @Column(name = "total_compra", nullable = false)
    private Double totalCompra;

    @NotNull(message = "El cliente es obligatorio")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}