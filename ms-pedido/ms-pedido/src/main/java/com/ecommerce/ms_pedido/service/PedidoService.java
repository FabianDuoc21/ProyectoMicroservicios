package com.ecommerce.ms_pedido.service;

import com.ecommerce.ms_pedido.model.Pedido;
import com.ecommerce.ms_pedido.repository.PedidoRepository;
import com.ecommerce.ms_pedido.webclient.ClienteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteClient clienteClient;

    public Pedido registrarPedido(Pedido pedido) {

        log.info("Validando cliente {}",
                pedido.getClienteId());

        Boolean existeCliente = clienteClient.existeCliente(pedido.getClienteId());


        if (existeCliente != null && existeCliente) {

            log.info("Cliente validado");

            Pedido nuevo =
                    pedidoRepository.save(pedido);

            log.info("Pedido creado ID {}",
                    nuevo.getIdCompra());

            return nuevo;
        }

        log.error("Cliente {} no encontrado",
                pedido.getClienteId());

        throw new RuntimeException(
                "Cliente no encontrado");
    }

    public List<Pedido> obtenerTodosLosPedidos() {

        log.info("Consultando pedidos");

        return pedidoRepository.findAll();
    }
    public void eliminarPedido(Long id) {

        log.info("Eliminando región {}", id);

        pedidoRepository.deleteById(id);
    }

    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pedido no encontrado"));

        pedido.setFechaCompra(
                pedidoActualizado.getFechaCompra());

        pedido.setCantidadEntrada(
                pedidoActualizado.getCantidadEntrada());

        pedido.setTotalCompra(
                pedidoActualizado.getTotalCompra());

        pedido.setClienteId(
                pedidoActualizado.getClienteId());

        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        log.info("Consultando pedido por ID {}", id);
        return pedidoRepository.findById(id);
    }
}