package com.ecommerce.ms_cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.ms_cliente.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}