package com.example.region.service;

import com.example.region.model.Region;
import com.example.region.repository.RegionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegionServiceTest {

    @Test
    void debeListarRegiones() {
        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        List<Region> regiones = List.of(new Region(1, "Metropolitana"));
        when(repository.findAll()).thenReturn(regiones);

        List<Region> resultado = service.getAllRegiones();

        assertEquals(1, resultado.size());
        assertEquals("Metropolitana", resultado.get(0).getNombreRegion());
        verify(repository).findAll();
    }

    @Test
    void debeBuscarRegionPorId() {
        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        Region region = new Region(1, "Metropolitana");
        when(repository.findById(1)).thenReturn(Optional.of(region));

        Optional<Region> resultado = service.getRegionById(1);

        assertTrue(resultado.isPresent());
        assertEquals("Metropolitana", resultado.get().getNombreRegion());
        verify(repository).findById(1);
    }

    @Test
    void debeCrearRegion() {
        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        Region region = new Region(1, "Valparaíso");
        when(repository.save(region)).thenReturn(region);

        Region resultado = service.createRegion(region);

        assertNotNull(resultado);
        assertEquals("Valparaíso", resultado.getNombreRegion());
        verify(repository).save(region);
    }

    @Test
    void debeLanzarErrorAlActualizarRegionInexistente() {
        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        Region region = new Region(99, "No existe");
        when(repository.existsById(99)).thenReturn(false);

        RuntimeException error = assertThrows(RuntimeException.class, () -> service.updateRegion(region));

        assertEquals("Región no encontrada", error.getMessage());
        verify(repository, never()).save(region);
    }
}