package com.ecommerce.ms_cliente.service;

import com.ecommerce.ms_cliente.model.Cliente;
import com.ecommerce.ms_cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existente = getCliente(id);
        existente.setNombreCliente(cliente.getNombreCliente());
        existente.setCorreoCliente(cliente.getCorreoCliente());
        existente.setTelefonoCliente(cliente.getTelefonoCliente());
        return clienteRepository.save(existente);
    }

    @Override
    public void deleteCliente(Long id) {
        Cliente existente = getCliente(id);
        clienteRepository.delete(existente);
    }
}