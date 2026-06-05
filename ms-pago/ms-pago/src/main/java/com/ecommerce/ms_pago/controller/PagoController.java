package com.ecommerce.ms_pago.controller;

import com.ecommerce.ms_pago.dto.PagoDTO;
import com.ecommerce.ms_pago.model.Pago;
import com.ecommerce.ms_pago.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<Pago> crearPago(
            @Valid @RequestBody PagoDTO dto) {

        Pago pago = new Pago();

        pago.setMontoPago(dto.getMontoPago());
        pago.setFechaPago(dto.getFechaPago());
        pago.setPedidoId(dto.getPedidoId());

        Pago nuevoPago = pagoService.registrarPago(pago);

        return ResponseEntity.status(201)
                .body(nuevoPago);
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {

        return ResponseEntity.ok(
                pagoService.obtenerTodosLosPagos()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(
            @PathVariable Long id,
            @Valid @RequestBody PagoDTO dto) {

        Pago pago = new Pago();

        pago.setMontoPago(dto.getMontoPago());
        pago.setFechaPago(dto.getFechaPago());
        pago.setPedidoId(dto.getPedidoId());

        return ResponseEntity.ok(
                pagoService.actualizarPago(id, pago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(
            @PathVariable Long id) {

        pagoService.eliminarPago(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pagoService.obtenerPagoPorId(id));
    }
}

