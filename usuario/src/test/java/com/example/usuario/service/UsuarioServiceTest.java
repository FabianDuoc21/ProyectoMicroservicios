package com.example.usuario.service;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.exception.ApiException;
import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Test
    void debeListarUsuarios() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setCorreo("juan@email.com");
        usuario.setContrasena("123456");

        when(repository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void debeBuscarUsuarioPorId() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setCorreo("juan@email.com");
        usuario.setContrasena("123456");

        when(repository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario resultado = service.buscar(1);

        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@email.com", resultado.getCorreo());
    }

    @Test
    void debeLanzarErrorSiUsuarioNoExiste() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        when(repository.findById(99)).thenReturn(Optional.empty());

        ApiException error = assertThrows(ApiException.class,
                () -> service.buscar(99));

        assertEquals("Usuario no encontrado", error.getMessage());
    }

    @Test
    void debeGuardarUsuario() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(1);
        dto.setNombre("Juan");
        dto.setApellido("Pérez");
        dto.setCorreo("juan@email.com");
        dto.setContrasena("123456");

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setCorreo("juan@email.com");
        usuario.setContrasena("123456");

        when(repository.existsById(1)).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = service.guardar(dto);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@email.com", resultado.getCorreo());
    }

    @Test
    void debeLanzarErrorSiIdYaExiste() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(1);
        dto.setNombre("Juan");
        dto.setApellido("Pérez");
        dto.setCorreo("juan@email.com");
        dto.setContrasena("123456");

        when(repository.existsById(1)).thenReturn(true);

        ApiException error = assertThrows(ApiException.class,
                () -> service.guardar(dto));

        assertEquals("Ya existe un usuario con ese ID", error.getMessage());

        verify(repository, never()).save(any(Usuario.class));
    }

    @Test
    void debeActualizarUsuario() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setCorreo("juan@email.com");
        usuario.setContrasena("123456");

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(1);
        dto.setNombre("Pedro");
        dto.setApellido("González");
        dto.setCorreo("pedro@email.com");
        dto.setContrasena("abcdef");

        when(repository.findById(1)).thenReturn(Optional.of(usuario));
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = service.actualizar(1, dto);

        assertEquals("Pedro", resultado.getNombre());
        assertEquals("pedro@email.com", resultado.getCorreo());
    }

    @Test
    void debeLanzarErrorAlActualizarUsuarioInexistente() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(99);
        dto.setNombre("No Existe");
        dto.setApellido("Usuario");
        dto.setCorreo("no@existe.cl");
        dto.setContrasena("123456");

        when(repository.findById(99)).thenReturn(Optional.empty());

        ApiException error = assertThrows(ApiException.class,
                () -> service.actualizar(99, dto));

        assertEquals("Usuario no encontrado", error.getMessage());
    }

    @Test
    void debeEliminarUsuario() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Juan");

        when(repository.findById(1)).thenReturn(Optional.of(usuario));

        service.eliminar(1);

        verify(repository).delete(usuario);
    }

    @Test
    void debeLanzarErrorAlEliminarUsuarioInexistente() {

        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repository);

        when(repository.findById(99)).thenReturn(Optional.empty());

        ApiException error = assertThrows(ApiException.class,
                () -> service.eliminar(99));

        assertEquals("Usuario no encontrado", error.getMessage());

        verify(repository, never()).delete(any(Usuario.class));
    }

}