package com.example.region.service;

import com.example.region.model.Region;
import com.example.region.repository.RegionRepository;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegiones() {

        log.info("Consultando regiones");

        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Integer id) {
        log.info("Consultando region con ID {}", id);
        return regionRepository.findById(id);
    }

    public Region createRegion(Region region) {

        log.info("Creando región {}",
                region.getNombreRegion());

        return regionRepository.save(region);
    }

    public void deleteRegion(Integer id) {

        log.info("Eliminando región {}", id);

        regionRepository.deleteById(id);
    }
    public Region updateRegion(Region region) {
        log.info("Actualizando región ID {}", region.getIdRegion());
        if (!regionRepository.existsById(region.getIdRegion())) {
            log.error("Región {} no encontrada", region.getIdRegion());
            throw new RuntimeException("Región no encontrada");
        }
        return regionRepository.save(region);
    }
}
