package com.ecommerce.ms_cliente;

import com.ecommerce.ms_cliente.model.Cliente;
import com.ecommerce.ms_cliente.repository.ClienteRepository;
import com.ecommerce.ms_cliente.service.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteServiceImpl clienteService;


	@Test
	void testObtenerClientePorId() {

		Cliente mockCliente = new Cliente(1L, "Juan", "juan@test.com", "12345");
		given(clienteRepository.findById(1L)).willReturn(Optional.of(mockCliente));


		Cliente resultado = clienteService.getCliente(1L);


		assertNotNull(resultado);
		assertEquals("Juan", resultado.getNombreCliente());
		assertEquals("juan@test.com", resultado.getCorreoCliente());
	}


	@Test
	void testObtenerClienteInexistenteLanzaExcepcion() {

		given(clienteRepository.findById(99L)).willReturn(Optional.empty());


		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> clienteService.getCliente(99L));

		assertEquals("Cliente no encontrado con ID: 99", ex.getMessage());
	}


	@Test
	void testObtenerTodosLosClientes() {

		List<Cliente> lista = List.of(
				new Cliente(1L, "Juan", "juan@test.com", "111"),
				new Cliente(2L, "Ana", "ana@test.com", "222")
		);
		given(clienteRepository.findAll()).willReturn(lista);


		List<Cliente> resultado = clienteService.getClientes();


		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		assertEquals("Ana", resultado.get(1).getNombreCliente());
	}


	@Test
	void testGuardarCliente() {

		Cliente nuevo = new Cliente(null, "Maria", "maria@test.com", "333");
		Cliente guardado = new Cliente(3L, "Maria", "maria@test.com", "333");
		given(clienteRepository.save(nuevo)).willReturn(guardado);


		Cliente resultado = clienteService.saveCliente(nuevo);


		assertNotNull(resultado);
		assertEquals(3L, resultado.getIdCliente());
		assertEquals("Maria", resultado.getNombreCliente());
		verify(clienteRepository, times(1)).save(nuevo);
	}



	@Test
	void testActualizarCliente() {

		Cliente existente = new Cliente(1L, "Juan", "juan@test.com", "111");
		Cliente datosNuevos = new Cliente(null, "Juan Actualizado", "juan2@test.com", "999");
		Cliente clienteActualizado = new Cliente(1L, "Juan Actualizado", "juan2@test.com", "999");

		given(clienteRepository.findById(1L)).willReturn(Optional.of(existente));
		given(clienteRepository.save(existente)).willReturn(clienteActualizado);


		Cliente resultado = clienteService.updateCliente(1L, datosNuevos);


		assertNotNull(resultado);
		assertEquals("Juan Actualizado", resultado.getNombreCliente());
		assertEquals("juan2@test.com", resultado.getCorreoCliente());
	}


	@Test
	void testEliminarCliente() {

		Cliente existente = new Cliente(1L, "Juan", "juan@test.com", "111");
		given(clienteRepository.findById(1L)).willReturn(Optional.of(existente));


		clienteService.deleteCliente(1L);


		verify(clienteRepository, times(1)).findById(1L);
		verify(clienteRepository, times(1)).delete(existente);
	}
}
