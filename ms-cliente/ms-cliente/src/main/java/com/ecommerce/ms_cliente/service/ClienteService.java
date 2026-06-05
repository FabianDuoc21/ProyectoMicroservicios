package com.ecommerce.ms_cliente.service;

import com.ecommerce.ms_cliente.model.Cliente;
import com.ecommerce.ms_cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {

        log.info("Consultando todos los clientes");

        return clienteRepository.findAll();
    }

    public Cliente getCliente(Long id) {

        log.info("Buscando cliente ID {}", id);

        return clienteRepository.findById(id)
                .orElseThrow(() -> {

                    log.error("Cliente {} no encontrado", id);

                    return new RuntimeException(
                            "Cliente no encontrado");
                });
    }

    public Cliente saveCliente(Cliente cliente) {

        log.info("Guardando cliente {}",
                cliente.getNombreCliente());

        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(
            Long id,
            Cliente clienteActualizado) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente no encontrado"));

        cliente.setNombreCliente(
                clienteActualizado.getNombreCliente());

        cliente.setCorreoCliente(
                clienteActualizado.getCorreoCliente());

        cliente.setTelefonoCliente(
                clienteActualizado.getTelefonoCliente());

        log.info("Actualizando cliente ID {}", id);

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente no encontrado"));

        log.info("Eliminando cliente ID {}", id);

        clienteRepository.delete(cliente);
    }
}