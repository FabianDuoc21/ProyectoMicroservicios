package com.ecommerce.ms_pago.controller;

import com.ecommerce.ms_pago.model.Pago;
import com.ecommerce.ms_pago.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.ecommerce.ms_pago.dto.PagoDTO;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Gestion de pagos del sistema")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Registrar un nuevo pago")
    public ResponseEntity<EntityModel<Pago>> crearPago(@Valid @RequestBody PagoDTO dto) {
        Pago pago = new Pago();
        pago.setMontoPago(dto.getMontoPago());
        pago.setFechaPago(dto.getFechaPago());
        pago.setPedidoId(dto.getPedidoId());

        Pago nuevoPago = pagoService.registrarPago(pago);

        return ResponseEntity.status(201)
                .body(EntityModel.of(nuevoPago,
                        linkTo(methodOn(PagoController.class).obtenerPagoPorId(nuevoPago.getIdPago())).withSelfRel(),
                        linkTo(methodOn(PagoController.class).listarPagos()).withRel("pagos")));
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los pagos")
    public ResponseEntity<CollectionModel<EntityModel<Pago>>> listarPagos() {
        List<EntityModel<Pago>> pagos = pagoService.obtenerTodosLosPagos().stream()
                .map(pago -> EntityModel.of(pago,
                        linkTo(methodOn(PagoController.class).obtenerPagoPorId(pago.getIdPago())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(pagos,
                linkTo(methodOn(PagoController.class).listarPagos()).withSelfRel()));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un pago existente")
    public ResponseEntity<EntityModel<Pago>> actualizarPago(@PathVariable Long id, @Valid @RequestBody PagoDTO dto) {
        Pago pago = new Pago();
        pago.setMontoPago(dto.getMontoPago());
        pago.setFechaPago(dto.getFechaPago());
        pago.setPedidoId(dto.getPedidoId());

        Pago actualizado = pagoService.actualizarPago(id, pago);

        return ResponseEntity.ok(EntityModel.of(actualizado,
                linkTo(methodOn(PagoController.class).obtenerPagoPorId(id)).withSelfRel()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago por ID")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un pago especifico por ID")
    public ResponseEntity<EntityModel<Pago>> obtenerPagoPorId(@PathVariable Long id) {
        Pago pago = pagoService.obtenerPagoPorId(id);
        return ResponseEntity.ok(EntityModel.of(pago,
                linkTo(methodOn(PagoController.class).obtenerPagoPorId(id)).withSelfRel(),
                linkTo(methodOn(PagoController.class).listarPagos()).withRel("pagos")));
    }
}