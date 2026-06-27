package com.ecommerce.ms_cliente.service;

import com.ecommerce.ms_cliente.model.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> getClientes();
    Cliente getCliente(Long id);
    Cliente saveCliente(Cliente cliente);
    Cliente updateCliente(Long id, Cliente cliente);
    void deleteCliente(Long id);
}