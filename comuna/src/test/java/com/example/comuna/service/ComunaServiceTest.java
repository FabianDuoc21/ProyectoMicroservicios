package com.example.comuna.service;

import com.example.comuna.model.Comuna;
import com.example.comuna.repository.ComunaRepository;
import com.example.comuna.webclient.RegionClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComunaServiceTest {

    @Test
    void debeListarComunas() {
        // Given
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = mock(RegionClient.class);
        ComunaService service = new ComunaService(repository, regionClient);

        List<Comuna> comunas = List.of(new Comuna(1, "Santiago", 1));
        when(repository.findAll()).thenReturn(comunas);

        // When
        List<Comuna> resultado = service.getAllComunas();

        // Then
        assertEquals(1, resultado.size());
        assertEquals("Santiago", resultado.get(0).getNombreComuna());
        verify(repository).findAll();
    }

    @Test
    void debeBuscarComunaPorId() {
        // Given
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = mock(RegionClient.class);
        ComunaService service = new ComunaService(repository, regionClient);

        Comuna comuna = new Comuna(1, "Providencia", 1);
        when(repository.findById(1)).thenReturn(Optional.of(comuna));

        // When
        Optional<Comuna> resultado = service.getComunaById(1);

        // Then
        assertTrue(resultado.isPresent());
        assertEquals("Providencia", resultado.get().getNombreComuna());
        verify(repository).findById(1);
    }

    @Test
    void debeCrearComunaCuandoRegionExiste() {
        // Given
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = mock(RegionClient.class);
        ComunaService service = new ComunaService(repository, regionClient);

        Comuna comuna = new Comuna(1, "Las Condes", 1);

        when(regionClient.existeRegion(1)).thenReturn(true);
        when(repository.save(comuna)).thenReturn(comuna);

        // When
        Comuna resultado = service.createComuna(comuna);

        // Then
        assertNotNull(resultado);
        assertEquals("Las Condes", resultado.getNombreComuna());
        verify(regionClient).existeRegion(1);
        verify(repository).save(comuna);
    }

    @Test
    void debeLanzarErrorAlCrearComunaConRegionInexistente() {
        // Given
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = mock(RegionClient.class);
        ComunaService service = new ComunaService(repository, regionClient);

        Comuna comuna = new Comuna(1, "Comuna inválida", 99);

        when(regionClient.existeRegion(99)).thenReturn(false);

        // When / Then
        RuntimeException error = assertThrows(RuntimeException.class, () -> service.createComuna(comuna));
        assertEquals("La región asociada no existe", error.getMessage());
        verify(repository, never()).save(comuna);
    }

    @Test
    void debeActualizarComunaExistente() {
        // Given
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = mock(RegionClient.class);
        ComunaService service = new ComunaService(repository, regionClient);

        Comuna comuna = new Comuna(1, "Ñuñoa", 1);

        when(repository.existsById(1)).thenReturn(true);
        when(regionClient.existeRegion(1)).thenReturn(true);
        when(repository.save(comuna)).thenReturn(comuna);

        // When
        Comuna resultado = service.updateComuna(comuna);

        // Then
        assertEquals("Ñuñoa", resultado.getNombreComuna());
        verify(repository).existsById(1);
        verify(regionClient).existeRegion(1);
        verify(repository).save(comuna);
    }

    @Test
    void debeLanzarErrorAlActualizarComunaInexistente() {
        // Given
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = mock(RegionClient.class);
        ComunaService service = new ComunaService(repository, regionClient);

        Comuna comuna = new Comuna(99, "No existe", 1);

        when(repository.existsById(99)).thenReturn(false);

        // When / Then
        RuntimeException error = assertThrows(RuntimeException.class, () -> service.updateComuna(comuna));
        assertEquals("Comuna no encontrada", error.getMessage());
        verify(repository, never()).save(comuna);
    }
}