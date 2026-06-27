package com.ecommerce.ms_pago;

import com.ecommerce.ms_pago.model.Pago;
import com.ecommerce.ms_pago.repository.PagoRepository;
import com.ecommerce.ms_pago.service.PagoService;
import com.ecommerce.ms_pago.webclient.PedidoClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MsPagoApplicationTests {

	@Mock
	private PagoRepository pagoRepository;

	@InjectMocks
	private PagoService pagoService;

	@Test
	void testObtenerPagoPorId() {
		Pago mockPago = new Pago(1L, 5000.0, LocalDate.of(2024, 6, 1), 1L);
		given(pagoRepository.findById(1L)).willReturn(Optional.of(mockPago));

		Pago resultado = pagoService.obtenerPagoPorId(1L);

		assertNotNull(resultado);
		assertEquals(1L, resultado.getIdPago());
		assertEquals(5000.0, resultado.getMontoPago());
	}

	@Test
	void testObtenerPagoInexistenteLanzaExcepcion() {
		given(pagoRepository.findById(99L)).willReturn(Optional.empty());

		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> pagoService.obtenerPagoPorId(99L));

		assertEquals("Pago no encontrado", ex.getMessage());
	}

	@Test
	void testObtenerTodosLosPagos() {
		List<Pago> lista = List.of(
				new Pago(1L, 1000.0, LocalDate.now(), 1L),
				new Pago(2L, 2000.0, LocalDate.now(), 2L)
		);
		given(pagoRepository.findAll()).willReturn(lista);

		List<Pago> resultado = pagoService.obtenerTodosLosPagos();

		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		assertEquals(2000.0, resultado.get(1).getMontoPago());
	}

	@Test
	void testRegistrarPago() {
		Pago nuevo = new Pago(null, 5000.0, LocalDate.now(), 1L);
		Pago guardado = new Pago(1L, 5000.0, LocalDate.now(), 1L);
		given(pagoRepository.save(nuevo)).willReturn(guardado);

		Pago resultado = pagoService.registrarPago(nuevo);

		assertNotNull(resultado);
		assertEquals(1L, resultado.getIdPago());
		assertEquals(5000.0, resultado.getMontoPago());
		verify(pagoRepository, times(1)).save(nuevo);
	}

	@Test
	void testActualizarPago() {
		Pago existente = new Pago(1L, 1000.0, LocalDate.of(2024, 1, 1), 1L);
		Pago datosNuevos = new Pago(null, 9999.0, LocalDate.now(), 2L);
		Pago actualizado = new Pago(1L, 9999.0, LocalDate.now(), 2L);

		given(pagoRepository.findById(1L)).willReturn(Optional.of(existente));
		given(pagoRepository.save(existente)).willReturn(actualizado);

		Pago resultado = pagoService.actualizarPago(1L, datosNuevos);

		assertNotNull(resultado);
		assertEquals(9999.0, resultado.getMontoPago());
		assertEquals(2L, resultado.getPedidoId());
	}

	@Test
	void testEliminarPago() {
		doNothing().when(pagoRepository).deleteById(1L);

		pagoService.eliminarPago(1L);

		verify(pagoRepository, times(1)).deleteById(1L);
	}
}