package com.example.usuario.controller;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;
import com.example.usuario.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios")
public class UsuarioController {

    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    @Operation(summary = "Listar usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){

        logger.info("Listando usuarios");

        return ResponseEntity.ok(usuarioService.listar());

    }

    @Operation(summary = "Buscar usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id){

        logger.info("Buscando usuario {}",id);

        return ResponseEntity.ok(usuarioService.buscar(id));

    }

    @Operation(summary = "Crear usuario")
    @PostMapping
    public ResponseEntity<Usuario> guardar(
            @Valid @RequestBody UsuarioDTO dto){

        logger.info("Creando usuario {}",dto.getCorreo());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.guardar(dto));

    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioDTO dto){

        logger.info("Actualizando usuario {}",id);

        return ResponseEntity.ok(
                usuarioService.actualizar(id,dto));

    }

    @Operation(summary = "Eliminar usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){

        logger.info("Eliminando usuario {}",id);

        usuarioService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}
