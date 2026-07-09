package com.ecommerce.ms_cliente.service;

import com.ecommerce.ms_cliente.model.Cliente;
import com.ecommerce.ms_cliente.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getClientes() {
        log.info("Consultando la lista de todos los clientes");
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getCliente(Long id) {
        log.info("Buscando cliente con ID: {}", id);

        return clienteRepository.findById(id)
                .orElseThrow(() -> {

                    log.error("Fallo al buscar: Cliente no encontrado con ID: {}", id);
                    return new RuntimeException("Cliente no encontrado con ID: " + id);
                });
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        log.info("Guardando nuevo cliente con nombre: {}", cliente.getNombreCliente());
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        log.info("Iniciando actualización para el cliente con ID: {}", id);
        Cliente existente = getCliente(id);

        existente.setNombreCliente(cliente.getNombreCliente());
        existente.setCorreoCliente(cliente.getCorreoCliente());
        existente.setTelefonoCliente(cliente.getTelefonoCliente());

        log.info("Cliente con ID: {} actualizado correctamente", id);
        return clienteRepository.save(existente);
    }

    @Override
    public void deleteCliente(Long id) {

        log.warn("Se va a eliminar el cliente con ID: {}", id);
        Cliente existente = getCliente(id);
        clienteRepository.delete(existente);
        log.info("Cliente con ID: {} eliminado exitosamente", id);
    }
}