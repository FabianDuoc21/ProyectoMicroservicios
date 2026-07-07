package com.example.comuna.controller;

import com.example.comuna.dto.ComunaDTO;
import com.example.comuna.model.Comuna;
import com.example.comuna.service.ComunaService;
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
@RequestMapping("/api/comunas")
@RequiredArgsConstructor
@Tag(name = "Comunas", description = "Microservicio de Comunas")
public class ComunaController {

    private static final Logger logger = LoggerFactory.getLogger(ComunaController.class);

    private final ComunaService comunaService;

    @Operation(summary = "Listar comunas")
    @GetMapping
    public ResponseEntity<List<Comuna>> listar(){

        logger.info("Listando comunas");

        return ResponseEntity.ok(comunaService.listar());
    }

    @Operation(summary = "Buscar comuna por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Comuna> buscar(@PathVariable Integer id){

        logger.info("Buscando comuna {}",id);

        return ResponseEntity.ok(comunaService.buscar(id));

    }

    @Operation(summary = "Crear comuna")
    @PostMapping
    public ResponseEntity<Comuna> guardar(@Valid @RequestBody ComunaDTO dto){

        logger.info("Creando comuna {}",dto.getNombreComuna());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comunaService.guardar(dto));

    }

    @Operation(summary = "Actualizar comuna")
    @PutMapping("/{id}")
    public ResponseEntity<Comuna> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ComunaDTO dto){

        logger.info("Actualizando comuna {}",id);

        return ResponseEntity.ok(
                comunaService.actualizar(id,dto));

    }

    @Operation(summary = "Eliminar comuna")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){

        logger.info("Eliminando comuna {}",id);

        comunaService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}
