package com.example.evento.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ComunaClient {

    private final RestTemplate restTemplate;

    public ComunaClient() {
        this.restTemplate = new RestTemplate();
    }

    public Boolean existeComuna(Long comunaId) {
        try {
            restTemplate.getForObject(
                    "http://localhost:8085/api/comunas/" + comunaId,
                    Object.class
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}