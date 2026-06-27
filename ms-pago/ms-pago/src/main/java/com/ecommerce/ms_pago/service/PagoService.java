package com.ecommerce.ms_pago.service;

import com.ecommerce.ms_pago.model.Pago;
import com.ecommerce.ms_pago.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    public Pago obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public Pago registrarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Pago actualizarPago(Long id, Pago pagoActualizado) {
        Pago pago = obtenerPagoPorId(id);
        pago.setMontoPago(pagoActualizado.getMontoPago());
        pago.setFechaPago(pagoActualizado.getFechaPago());
        pago.setPedidoId(pagoActualizado.getPedidoId());
        return pagoRepository.save(pago);
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}