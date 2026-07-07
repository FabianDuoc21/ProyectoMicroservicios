package com.example.comuna.service;

import com.example.comuna.dto.ComunaDTO;
import com.example.comuna.exception.ApiException;
import com.example.comuna.model.Comuna;
import com.example.comuna.repository.ComunaRepository;
import com.example.comuna.webclient.RegionClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComunaServiceTest {

    static class FakeRegionClient extends RegionClient {
        private final boolean existe;

        FakeRegionClient(boolean existe) {
            this.existe = existe;
        }

        @Override
        public boolean existeRegion(Integer idRegion) {
            return existe;
        }
    }

    @Test
    void debeListarComunas() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(true);
        ComunaService service = new ComunaService(repository, regionClient);

        when(repository.findAll()).thenReturn(List.of(new Comuna(1, "Santiago", 1)));

        List<Comuna> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals("Santiago", resultado.get(0).getNombreComuna());
    }

    @Test
    void debeBuscarComunaPorId() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(true);
        ComunaService service = new ComunaService(repository, regionClient);

        Comuna comuna = new Comuna(1, "Providencia", 1);
        when(repository.findById(1)).thenReturn(Optional.of(comuna));

        Comuna resultado = service.buscar(1);

        assertEquals("Providencia", resultado.getNombreComuna());
    }

    @Test
    void debeLanzarErrorSiComunaNoExiste() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(true);
        ComunaService service = new ComunaService(repository, regionClient);

        when(repository.findById(99)).thenReturn(Optional.empty());

        ApiException error = assertThrows(ApiException.class, () -> service.buscar(99));

        assertEquals("La comuna no existe", error.getMessage());
    }

    @Test
    void debeCrearComunaCuandoRegionExiste() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(true);
        ComunaService service = new ComunaService(repository, regionClient);

        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(1);
        dto.setNombreComuna("Las Condes");
        dto.setIdRegion(1);

        Comuna comuna = new Comuna(1, "Las Condes", 1);

        when(repository.existsById(1)).thenReturn(false);
        when(repository.save(any(Comuna.class))).thenReturn(comuna);

        Comuna resultado = service.guardar(dto);

        assertNotNull(resultado);
        assertEquals("Las Condes", resultado.getNombreComuna());
    }

    @Test
    void debeLanzarErrorAlCrearComunaConIdRepetido() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(true);
        ComunaService service = new ComunaService(repository, regionClient);

        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(1);
        dto.setNombreComuna("Las Condes");
        dto.setIdRegion(1);

        when(repository.existsById(1)).thenReturn(true);

        ApiException error = assertThrows(ApiException.class, () -> service.guardar(dto));

        assertEquals("Ya existe una comuna con ese ID", error.getMessage());
        verify(repository, never()).save(any(Comuna.class));
    }

    @Test
    void debeLanzarErrorAlCrearComunaConRegionInexistente() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(false);
        ComunaService service = new ComunaService(repository, regionClient);

        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(2);
        dto.setNombreComuna("Comuna inválida");
        dto.setIdRegion(99);

        when(repository.existsById(2)).thenReturn(false);

        ApiException error = assertThrows(ApiException.class, () -> service.guardar(dto));

        assertEquals("La región indicada no existe", error.getMessage());
        verify(repository, never()).save(any(Comuna.class));
    }

    @Test
    void debeActualizarComunaExistente() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(true);
        ComunaService service = new ComunaService(repository, regionClient);

        Comuna comuna = new Comuna(1, "Ñuñoa", 1);

        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(1);
        dto.setNombreComuna("Providencia");
        dto.setIdRegion(1);

        when(repository.findById(1)).thenReturn(Optional.of(comuna));
        when(repository.save(any(Comuna.class))).thenReturn(comuna);

        Comuna resultado = service.actualizar(1, dto);

        assertEquals("Providencia", resultado.getNombreComuna());
    }

    @Test
    void debeLanzarErrorAlActualizarComunaInexistente() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(true);
        ComunaService service = new ComunaService(repository, regionClient);

        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(99);
        dto.setNombreComuna("No existe");
        dto.setIdRegion(1);

        when(repository.findById(99)).thenReturn(Optional.empty());

        ApiException error = assertThrows(ApiException.class, () -> service.actualizar(99, dto));

        assertEquals("Comuna no encontrada", error.getMessage());
    }

    @Test
    void debeEliminarComunaExistente() {
        ComunaRepository repository = mock(ComunaRepository.class);
        RegionClient regionClient = new FakeRegionClient(true);
        ComunaService service = new ComunaService(repository, regionClient);

        Comuna comuna = new Comuna(1, "Santiago", 1);

        when(repository.findById(1)).thenReturn(Optional.of(comuna));

        service.eliminar(1);

        verify(repository).delete(comuna);
    }
}