package com.ecommerce.ms_cliente.controller;

import com.ecommerce.ms_cliente.DTO.ClienteDTO;
import com.ecommerce.ms_cliente.model.Cliente;
import com.ecommerce.ms_cliente.service.ClienteService;
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

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Gestión de clientes del sistema")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener todos los clientes (HATEOAS)")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> obtenerClientes() {
        List<Cliente> clientes = clienteService.getClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<Cliente>> clientesModel = clientes.stream()
                .map(cliente -> EntityModel.of(cliente,
                        linkTo(methodOn(ClienteController.class).obtenerCliente(cliente.getIdCliente())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(clientesModel,
                linkTo(methodOn(ClienteController.class).obtenerClientes()).withSelfRel()));
    }

    @Operation(summary = "Obtener cliente por ID")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.getCliente(id);
        return ResponseEntity.ok(EntityModel.of(cliente,
                linkTo(methodOn(ClienteController.class).obtenerCliente(id)).withSelfRel(),
                linkTo(methodOn(ClienteController.class).obtenerClientes()).withRel("clientes")));
    }

    @Operation(summary = "Guardar nuevo cliente")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> guardarCliente(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombreCliente(dto.getNombreCliente());
        cliente.setCorreoCliente(dto.getCorreoCliente());
        cliente.setTelefonoCliente(dto.getTelefonoCliente());

        Cliente nuevo = clienteService.saveCliente(cliente);
        return ResponseEntity.status(201).body(EntityModel.of(nuevo,
                linkTo(methodOn(ClienteController.class).obtenerCliente(nuevo.getIdCliente())).withSelfRel()));
    }

    @Operation(summary = "Actualizar cliente")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombreCliente(dto.getNombreCliente());
        cliente.setCorreoCliente(dto.getCorreoCliente());
        cliente.setTelefonoCliente(dto.getTelefonoCliente());

        Cliente actualizado = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(EntityModel.of(actualizado,
                linkTo(methodOn(ClienteController.class).obtenerCliente(id)).withSelfRel()));
    }

    @Operation(summary = "Eliminar cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}