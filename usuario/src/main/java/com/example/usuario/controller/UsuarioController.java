package com.example.usuario.controller;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;
import com.example.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody UsuarioDTO dto) {

        Usuario usuario = new Usuario(
                dto.getIdUsuario(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getCorreo(),
                dto.getContrasena()
        );

        return ResponseEntity.ok(
                usuarioService.createUsuario(usuario)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioDTO dto) {

        Usuario usuario = new Usuario(
                id,
                dto.getNombre(),
                dto.getApellido(),
                dto.getCorreo(),
                dto.getContrasena()
        );

        return ResponseEntity.ok(
                usuarioService.updateUsuario(usuario)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
