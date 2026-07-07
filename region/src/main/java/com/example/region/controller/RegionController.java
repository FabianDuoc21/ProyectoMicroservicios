package com.example.region.controller;

import com.example.region.dto.RegionDTO;
import com.example.region.model.Region;
import com.example.region.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regiones")
@RequiredArgsConstructor
@Tag(name = "Regiones")
public class RegionController {

    private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

    private final RegionService regionService;

    @Operation(summary = "Obtiene todas las regiones")
    @GetMapping
    public ResponseEntity<List<Region>> listar() {

        logger.info("Listando regiones");

        return ResponseEntity.ok(regionService.listar());
    }

    @Operation(summary = "Obtiene una región por id")
    @GetMapping("/{id}")
    public ResponseEntity<Region> buscar(@PathVariable Integer id){

        logger.info("Buscando región {}", id);

        return ResponseEntity.ok(regionService.buscar(id));
    }

    @Operation(summary = "Crear región")
    @PostMapping
    public ResponseEntity<Region> guardar(@Valid @RequestBody RegionDTO dto){

        logger.info("Creando región {}", dto.getNombreRegion());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(regionService.guardar(dto));
    }

    @Operation(summary = "Actualizar región")
    @PutMapping("/{id}")
    public ResponseEntity<Region> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody RegionDTO dto){

        logger.info("Actualizando región {}", id);

        return ResponseEntity.ok(regionService.actualizar(id,dto));

    }

    @Operation(summary = "Eliminar región")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){

        logger.info("Eliminando región {}", id);

        regionService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}
