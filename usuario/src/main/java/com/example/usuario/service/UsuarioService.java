package com.example.usuario.service;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.exception.ApiException;
import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository repository;

    public List<Usuario> listar(){

        logger.info("Listando usuarios");

        return repository.findAll();

    }

    public Usuario buscar(Integer id){

        logger.info("Buscando usuario {}",id);

        return repository.findById(id)
                .orElseThrow(()->
                        new ApiException("Usuario no encontrado"));

    }

    public Usuario guardar(UsuarioDTO dto){

        logger.info("Guardando usuario");

        if(repository.existsById(dto.getIdUsuario())){

            throw new ApiException("Ya existe un usuario con ese ID");

        }

        Usuario usuario=new Usuario();

        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());

        return repository.save(usuario);

    }

    public Usuario actualizar(Integer id,UsuarioDTO dto){

        Usuario usuario=repository.findById(id)
                .orElseThrow(()->
                        new ApiException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());

        logger.info("Actualizando usuario {}",id);

        return repository.save(usuario);

    }

    public void eliminar(Integer id){

        Usuario usuario=repository.findById(id)
                .orElseThrow(()->
                        new ApiException("Usuario no encontrado"));

        repository.delete(usuario);

        logger.info("Usuario eliminado");

    }

}