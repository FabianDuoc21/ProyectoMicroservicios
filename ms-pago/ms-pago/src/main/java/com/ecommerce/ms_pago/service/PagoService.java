package com.ecommerce.ms_pago.service;

import com.ecommerce.ms_pago.model.Pago;
import com.ecommerce.ms_pago.repository.PagoRepository;
import com.ecommerce.ms_pago.webclient.PedidoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PedidoClient pedidoClient;

    public Pago registrarPago(Pago pago) {

        log.info("Validando pedido {}",
                pago.getPedidoId());

        Boolean existePedido = pedidoClient.existePedido(pago.getPedidoId());

        if (existePedido != null && existePedido) {

            log.info("Pedido validado");

            Pago nuevo =
                    pagoRepository.save(pago);

            log.info("Pago registrado ID {}",
                    nuevo.getIdPago());

            return nuevo;
        }

        log.error("Pedido {} no encontrado",
                pago.getPedidoId());

        throw new RuntimeException(
                "Pedido no encontrado");
    }

    public List<Pago> obtenerTodosLosPagos() {

        log.info("Consultando pagos");

        return pagoRepository.findAll();
    }

    public Pago actualizarPago(Long id, Pago pagoActualizado) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pago no encontrado"));

        pago.setMontoPago(
                pagoActualizado.getMontoPago());

        pago.setFechaPago(
                pagoActualizado.getFechaPago());

        pago.setPedidoId(
                pagoActualizado.getPedidoId());

        return pagoRepository.save(pago);
    }

    public void eliminarPago(Long id) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pago no encontrado"));

        log.info("Eliminando pago ID {}", id);

        pagoRepository.delete(pago);
    }

    public Pago obtenerPagoPorId(Long id) {

        return pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pago no encontrado"));
    }
}