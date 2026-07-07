package com.example.region.service;

import com.example.region.dto.RegionDTO;
import com.example.region.exception.ApiException;
import com.example.region.model.Region;
import com.example.region.repository.RegionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegionServiceTest {

    @Test
    void debeListarRegiones() {

        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        when(repository.findAll()).thenReturn(
                List.of(new Region(1, "Región Metropolitana"))
        );

        List<Region> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals("Región Metropolitana", resultado.get(0).getNombreRegion());
    }

    @Test
    void debeBuscarRegionPorId() {

        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        Region region = new Region(1, "Valparaíso");

        when(repository.findById(1)).thenReturn(Optional.of(region));

        Region resultado = service.buscar(1);

        assertEquals("Valparaíso", resultado.getNombreRegion());
    }

    @Test
    void debeLanzarErrorSiRegionNoExiste() {

        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        when(repository.findById(99)).thenReturn(Optional.empty());

        ApiException error = assertThrows(ApiException.class,
                () -> service.buscar(99));

        assertEquals("La región no existe", error.getMessage());

    }

    @Test
    void debeCrearRegion() {

        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(1);
        dto.setNombreRegion("Biobío");

        Region region = new Region(1, "Biobío");

        when(repository.existsById(1)).thenReturn(false);
        when(repository.save(any(Region.class))).thenReturn(region);

        Region resultado = service.guardar(dto);

        assertNotNull(resultado);
        assertEquals("Biobío", resultado.getNombreRegion());

    }

    @Test
    void debeLanzarErrorSiIdExiste() {

        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(1);
        dto.setNombreRegion("Biobío");

        when(repository.existsById(1)).thenReturn(true);

        ApiException error = assertThrows(ApiException.class,
                () -> service.guardar(dto));

        assertEquals("Ya existe una región con ese ID", error.getMessage());

    }

    @Test
    void debeActualizarRegion() {

        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        Region region = new Region(1, "RM");

        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(1);
        dto.setNombreRegion("Región Metropolitana");

        when(repository.findById(1)).thenReturn(Optional.of(region));
        when(repository.save(any(Region.class))).thenReturn(region);

        Region resultado = service.actualizar(1, dto);

        assertEquals("Región Metropolitana", resultado.getNombreRegion());

    }

    @Test
    void debeLanzarErrorAlActualizarRegionInexistente() {

        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(99);
        dto.setNombreRegion("No existe");

        when(repository.findById(99)).thenReturn(Optional.empty());

        ApiException error = assertThrows(ApiException.class,
                () -> service.actualizar(99, dto));

        assertEquals("Región no encontrada", error.getMessage());

    }

    @Test
    void debeEliminarRegion() {

        RegionRepository repository = mock(RegionRepository.class);
        RegionService service = new RegionService(repository);

        Region region = new Region(1, "Biobío");

        when(repository.findById(1)).thenReturn(Optional.of(region));

        service.eliminar(1);

        verify(repository).delete(region);

    }

}