package com.example.region.service;

import com.example.region.dto.RegionDTO;
import com.example.region.exception.ApiException;
import com.example.region.model.Region;
import com.example.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private static final Logger logger = LoggerFactory.getLogger(RegionService.class);

    private final RegionRepository repository;

    public List<Region> listar(){

        logger.info("Obteniendo listado de regiones");

        return repository.findAll();

    }

    public Region buscar(Integer id){

        logger.info("Buscando región {}", id);

        return repository.findById(id)
                .orElseThrow(() ->
                        new ApiException("La región no existe"));
    }

    public Region guardar(RegionDTO dto){

        logger.info("Guardando región");

        if(repository.existsById(dto.getIdRegion())){

            throw new ApiException("Ya existe una región con ese ID");

        }

        Region region=new Region();

        region.setIdRegion(dto.getIdRegion());
        region.setNombreRegion(dto.getNombreRegion());

        return repository.save(region);

    }

    public Region actualizar(Integer id,RegionDTO dto){

        Region region=repository.findById(id)
                .orElseThrow(() ->
                        new ApiException("Región no encontrada"));

        region.setNombreRegion(dto.getNombreRegion());

        logger.info("Actualizando región {}",id);

        return repository.save(region);

    }

    public void eliminar(Integer id){

        Region region=repository.findById(id)
                .orElseThrow(() ->
                        new ApiException("Región no encontrada"));

        repository.delete(region);

        logger.info("Región eliminada");

    }

}
