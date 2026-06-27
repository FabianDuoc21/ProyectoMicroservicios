package com.ecommerce.ms_pedido.controller;

import com.ecommerce.ms_pedido.dto.PedidoDTO;
import com.ecommerce.ms_pedido.model.Pedido;
import com.ecommerce.ms_pedido.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos")
    @Operation(summary = "Crear pedido", description = "Crea cada pedido")
    public ResponseEntity<Pedido> crearPedido(
            @Valid @RequestBody PedidoDTO dto) {

        Pedido pedido = new Pedido();

        pedido.setFechaCompra(dto.getFechaCompra());
        pedido.setCantidadEntrada(dto.getCantidadEntrada());
        pedido.setTotalCompra(dto.getTotalCompra());
        pedido.setClienteId(dto.getClienteId());

        Pedido nuevoPedido =
                pedidoService.registrarPedido(pedido);

        return ResponseEntity.status(201)
                .body(nuevoPedido);
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los pedidos", description = "Obtiene los pedidos ingresados")
    public ResponseEntity<List<Pedido>> listarPedidos() {

        return ResponseEntity.ok(
                pedidoService.obtenerTodosLosPedidos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene pedidos por código", description = "Obtiene cada pedido por código")
    public ResponseEntity<Pedido> obtenerPedidoPorId(
            @PathVariable Long id) {

        return pedidoService.obtenerPedidoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedido", description = "Elimina pedidos")
    public ResponseEntity<Void> eliminarPedido(
            @PathVariable Long id){

        pedidoService.eliminarPedido(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pedido", description = "Actualiza pedidos")
    public ResponseEntity<Pedido> actualizarPedido(
            @PathVariable Long id,
            @Valid @RequestBody PedidoDTO dto) {

        Pedido pedido = new Pedido();

        pedido.setFechaCompra(dto.getFechaCompra());
        pedido.setCantidadEntrada(dto.getCantidadEntrada());
        pedido.setTotalCompra(dto.getTotalCompra());
        pedido.setClienteId(dto.getClienteId());

        return ResponseEntity.ok(
                pedidoService.actualizarPedido(id, pedido));
    }
}