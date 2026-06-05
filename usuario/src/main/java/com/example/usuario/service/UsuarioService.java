package com.example.usuario.service;

import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        log.info("Consultando todos los usuarios");
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Integer id) {
        log.info("Buscando usuario con ID {}", id);
        return usuarioRepository.findById(id);
    }

    public Usuario updateUsuario(Usuario usuario) {
        log.info("Actualizando usuario ID {}", usuario.getIdUsuario());
        if (!usuarioRepository.existsById(usuario.getIdUsuario())) {
            log.error("Usuario {} no encontrado", usuario.getIdUsuario());
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuarioRepository.save(usuario);
    }


    public Usuario saveUsuario(Usuario usuario) {

        log.info("Guardando usuario {}",
                usuario.getNombre());

        return usuarioRepository.save(usuario);
    }

    public Usuario createUsuario(Usuario usuario) {
        log.info("Creando usuario: {}", usuario.getNombre());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Integer id) {
        log.info("Eliminando usuario ID {}", id);
        if (!usuarioRepository.existsById(id)) {
            log.error("Usuario {} no encontrado", id);
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
