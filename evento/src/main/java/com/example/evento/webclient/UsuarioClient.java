package com.example.evento.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {

    private final RestTemplate restTemplate;

    public UsuarioClient() {
        this.restTemplate = new RestTemplate();
    }

    public Boolean existeUsuario(Long usuarioId) {
        try {
            restTemplate.getForObject(
                    "http://localhost:8086/api/usuarios/" + usuarioId,
                    Object.class
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}