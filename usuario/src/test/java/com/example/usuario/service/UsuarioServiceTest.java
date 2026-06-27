package com.example.usuario.service;

import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Test
    void debeListarUsuarios() {
        // Given
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        List<Usuario> usuarios = List.of(
                new Usuario(1, "Juan", "Pérez", "juan@test.cl", "1234")
        );

        when(repository.findAll()).thenReturn(usuarios);

        // When
        List<Usuario> resultado = service.getAllUsuarios();

        // Then
        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
        verify(repository).findAll();
    }

    @Test
    void debeBuscarUsuarioPorId() {
        // Given
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        Usuario usuario = new Usuario(1, "Ana", "Gómez", "ana@test.cl", "1234");

        when(repository.findById(1)).thenReturn(Optional.of(usuario));

        // When
        Optional<Usuario> resultado = service.getUsuarioById(1);

        // Then
        assertTrue(resultado.isPresent());
        assertEquals("Ana", resultado.get().getNombre());
        verify(repository).findById(1);
    }

    @Test
    void debeCrearUsuario() {
        // Given
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        Usuario usuario = new Usuario(1, "Pedro", "Soto", "pedro@test.cl", "1234");

        when(repository.save(usuario)).thenReturn(usuario);

        // When
        Usuario resultado = service.createUsuario(usuario);

        // Then
        assertNotNull(resultado);
        assertEquals("Pedro", resultado.getNombre());
        assertEquals("pedro@test.cl", resultado.getCorreo());
        verify(repository).save(usuario);
    }

    @Test
    void debeActualizarUsuarioExistente() {
        // Given
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        Usuario usuario = new Usuario(1, "Camila", "Rojas", "camila@test.cl", "1234");

        when(repository.existsById(1)).thenReturn(true);
        when(repository.save(usuario)).thenReturn(usuario);

        // When
        Usuario resultado = service.updateUsuario(usuario);

        // Then
        assertEquals("Camila", resultado.getNombre());
        verify(repository).existsById(1);
        verify(repository).save(usuario);
    }

    @Test
    void debeLanzarErrorAlActualizarUsuarioInexistente() {
        // Given
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        Usuario usuario = new Usuario(99, "No", "Existe", "no@test.cl", "1234");

        when(repository.existsById(99)).thenReturn(false);

        // When / Then
        RuntimeException error = assertThrows(RuntimeException.class, () -> service.updateUsuario(usuario));
        assertEquals("Usuario no encontrado", error.getMessage());
        verify(repository, never()).save(usuario);
    }

    @Test
    void debeEliminarUsuarioExistente() {
        // Given
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        when(repository.existsById(1)).thenReturn(true);

        // When
        service.deleteUsuario(1);

        // Then
        verify(repository).existsById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void debeLanzarErrorAlEliminarUsuarioInexistente() {
        // Given
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        when(repository.existsById(99)).thenReturn(false);

        // When / Then
        RuntimeException error = assertThrows(RuntimeException.class, () -> service.deleteUsuario(99));
        assertEquals("Usuario no encontrado", error.getMessage());
        verify(repository, never()).deleteById(99);
    }
}
