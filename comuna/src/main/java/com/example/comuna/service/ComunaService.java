package com.example.comuna.service;

import com.example.comuna.model.Comuna;
import com.example.comuna.repository.ComunaRepository;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> getAllComunas() {

        log.info("Consultando comunas");

        return comunaRepository.findAll();
    }

    public Optional<Comuna> getComunaById(Integer id) {
        log.info("Buscando comuna con ID {}", id);
        return comunaRepository.findById(id);
    }

    public Comuna createComuna(Comuna comuna) {

        log.info("Creando comuna {}",
                comuna.getNombreComuna());

        return comunaRepository.save(comuna);
    }

    public void deleteComuna(Integer id) {

        log.info("Eliminando comuna {}", id);

        comunaRepository.deleteById(id);
    }
    public Comuna updateComuna(Comuna comuna) {
        log.info("Actualizando comuna ID {}", comuna.getIdComuna());
        if (!comunaRepository.existsById(comuna.getIdComuna())) {
            log.error("Comuna {} no encontrada", comuna.getIdComuna());
            throw new RuntimeException("Comuna no encontrada");
        }
        return comunaRepository.save(comuna);
    }
}
