package com.example.region.service;

import com.example.region.model.Region;
import com.example.region.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAllRegiones() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Integer id) {
        return regionRepository.findById(id);
    }

    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region updateRegion(Region region) {
        if (!regionRepository.existsById(region.getIdRegion())) {
            throw new RuntimeException("Región no encontrada");
        }
        return regionRepository.save(region);
    }

    public void deleteRegion(Integer id) {
        if (!regionRepository.existsById(id)) {
            throw new RuntimeException("Región no encontrada");
        }
        regionRepository.deleteById(id);
    }
}
