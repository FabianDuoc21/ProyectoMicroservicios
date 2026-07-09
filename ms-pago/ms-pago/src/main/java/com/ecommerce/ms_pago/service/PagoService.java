package com.ecommerce.ms_pago.service;

import com.ecommerce.ms_pago.model.Pago;
import com.ecommerce.ms_pago.repository.PagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> obtenerTodosLosPagos() {
        log.info("Consultando la lista completa de pagos");
        return pagoRepository.findAll();
    }

    public Pago obtenerPagoPorId(Long id) {
        log.info("Buscando pago con ID: {}", id);
        return pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Fallo la busqueda: Pago con ID {} no existe", id);
                    return new RuntimeException("Pago no encontrado");
                });
    }

    public Pago registrarPago(Pago pago) {
        log.info("Registrando nuevo pago por un monto de: {}", pago.getMontoPago());
        Pago pagoGuardado = pagoRepository.save(pago);
        log.info("Pago registrado exitosamente con ID: {}", pagoGuardado.getIdPago());
        return pagoGuardado;
    }

    public Pago actualizarPago(Long id, Pago pagoActualizado) {
        log.info("Iniciando actualizacion para el pago con ID: {}", id);
        Pago pago = obtenerPagoPorId(id);
        pago.setMontoPago(pagoActualizado.getMontoPago());
        pago.setFechaPago(pagoActualizado.getFechaPago());
        pago.setPedidoId(pagoActualizado.getPedidoId());

        Pago pagoModificado = pagoRepository.save(pago);
        log.info("Pago con ID {} actualizado correctamente", pagoModificado.getIdPago());
        return pagoModificado;
    }

    public void eliminarPago(Long id) {
        log.warn("Se va a eliminar el pago con ID: {}", id);
        pagoRepository.deleteById(id);
        log.info("Pago con ID {} eliminado", id);
    }
}