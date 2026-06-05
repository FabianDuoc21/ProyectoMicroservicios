package com.example.comuna.controller;

import com.example.comuna.dto.ComunaDTO;
import com.example.comuna.model.Comuna;
import com.example.comuna.service.ComunaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comunas")
public class ComunaController {

    private final ComunaService comunaService;

    public ComunaController(ComunaService comunaService) {
        this.comunaService = comunaService;
    }

    @GetMapping
    public List<Comuna> getAllComunas() {
        return comunaService.getAllComunas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> getComunaById(@PathVariable Integer id) {
        Optional<Comuna> comuna = comunaService.getComunaById(id);

        return comuna.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comuna> createComuna(@Valid @RequestBody ComunaDTO dto) {

        Comuna comuna = new Comuna(
                dto.getIdComuna(),
                dto.getNombreComuna(),
                dto.getIdRegion()
        );

        return ResponseEntity.ok(
                comunaService.createComuna(comuna)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> updateComuna(
            @PathVariable Integer id,
            @Valid @RequestBody ComunaDTO dto) {

        Comuna comuna = new Comuna(
                id,
                dto.getNombreComuna(),
                dto.getIdRegion()
        );

        return ResponseEntity.ok(
                comunaService.updateComuna(comuna)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComuna(@PathVariable Integer id) {
        comunaService.deleteComuna(id);
        return ResponseEntity.noContent().build();
    }
}
