package com.ecommerce.ms_cliente.controller;

import com.ecommerce.ms_cliente.dto.ClienteDTO;
import com.ecommerce.ms_cliente.model.Cliente;
import com.ecommerce.ms_cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {

        List<Cliente> clientes =
                clienteService.getClientes();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clienteService.getCliente(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(
            @Valid @RequestBody ClienteDTO dto) {

        Cliente cliente = new Cliente();

        cliente.setNombreCliente(
                dto.getNombreCliente());

        cliente.setCorreoCliente(
                dto.getCorreoCliente());

        cliente.setTelefonoCliente(
                dto.getTelefonoCliente());

        return ResponseEntity.status(201)
                .body(clienteService.saveCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO dto) {

        Cliente cliente = new Cliente();

        cliente.setNombreCliente(
                dto.getNombreCliente());

        cliente.setCorreoCliente(
                dto.getCorreoCliente());

        cliente.setTelefonoCliente(
                dto.getTelefonoCliente());

        return ResponseEntity.ok(
                clienteService.updateCliente(
                        id,
                        cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(
            @PathVariable Long id) {

        clienteService.deleteCliente(id);

        return ResponseEntity.noContent().build();
    }
}