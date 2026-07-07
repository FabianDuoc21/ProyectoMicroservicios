package com.example.comuna.service;

import com.example.comuna.dto.ComunaDTO;
import com.example.comuna.exception.ApiException;
import com.example.comuna.model.Comuna;
import com.example.comuna.repository.ComunaRepository;
import com.example.comuna.webclient.RegionClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComunaService {

    private static final Logger logger = LoggerFactory.getLogger(ComunaService.class);

    private final ComunaRepository repository;
    private final RegionClient regionClient;

    public List<Comuna> listar() {
        logger.info("Obteniendo listado de comunas");
        return repository.findAll();
    }

    public Comuna buscar(Integer id) {
        logger.info("Buscando comuna {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new ApiException("La comuna no existe"));
    }

    public Comuna guardar(ComunaDTO dto) {
        logger.info("Guardando comuna");

        if (repository.existsById(dto.getIdComuna())) {
            throw new ApiException("Ya existe una comuna con ese ID");
        }

        if (!regionClient.existeRegion(dto.getIdRegion())) {
            logger.warn("La región indicada no existe: {}", dto.getIdRegion());
            throw new ApiException("La región indicada no existe");
        }

        Comuna comuna = new Comuna();
        comuna.setIdComuna(dto.getIdComuna());
        comuna.setNombreComuna(dto.getNombreComuna());
        comuna.setIdRegion(dto.getIdRegion());

        return repository.save(comuna);
    }

    public Comuna actualizar(Integer id, ComunaDTO dto) {
        logger.info("Actualizando comuna {}", id);

        Comuna comuna = repository.findById(id)
                .orElseThrow(() -> new ApiException("Comuna no encontrada"));

        if (!regionClient.existeRegion(dto.getIdRegion())) {
            logger.warn("La región indicada no existe: {}", dto.getIdRegion());
            throw new ApiException("La región indicada no existe");
        }

        comuna.setNombreComuna(dto.getNombreComuna());
        comuna.setIdRegion(dto.getIdRegion());

        return repository.save(comuna);
    }

    public void eliminar(Integer id) {
        logger.info("Eliminando comuna {}", id);

        Comuna comuna = repository.findById(id)
                .orElseThrow(() -> new ApiException("Comuna no encontrada"));

        repository.delete(comuna);

        logger.info("Comuna eliminada {}", id);
    }
}