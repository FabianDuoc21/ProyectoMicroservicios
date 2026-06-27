package com.example.comuna.service;

import com.example.comuna.model.Comuna;
import com.example.comuna.repository.ComunaRepository;
import com.example.comuna.webclient.RegionClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComunaService {

    private final ComunaRepository comunaRepository;
    private final RegionClient regionClient;

    public ComunaService(ComunaRepository comunaRepository, RegionClient regionClient) {
        this.comunaRepository = comunaRepository;
        this.regionClient = regionClient;
    }

    public List<Comuna> getAllComunas() {
        return comunaRepository.findAll();
    }

    public Optional<Comuna> getComunaById(Integer id) {
        return comunaRepository.findById(id);
    }

    public Comuna createComuna(Comuna comuna) {
        if (!regionClient.existeRegion(comuna.getIdRegion())) {
            throw new RuntimeException("La región asociada no existe");
        }

        return comunaRepository.save(comuna);
    }

    public Comuna updateComuna(Comuna comuna) {
        if (!comunaRepository.existsById(comuna.getIdComuna())) {
            throw new RuntimeException("Comuna no encontrada");
        }

        if (!regionClient.existeRegion(comuna.getIdRegion())) {
            throw new RuntimeException("La región asociada no existe");
        }

        return comunaRepository.save(comuna);
    }

    public void deleteComuna(Integer id) {
        if (!comunaRepository.existsById(id)) {
            throw new RuntimeException("Comuna no encontrada");
        }

        comunaRepository.deleteById(id);
    }
}
