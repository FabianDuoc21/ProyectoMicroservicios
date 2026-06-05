package com.ecommerce.ms_pedido.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EventoClient {

    private final RestTemplate restTemplate;

    public EventoClient() {
        this.restTemplate = new RestTemplate();
    }

    public Boolean existeEvento(Long eventoId) {
        try {
            restTemplate.getForObject(
                    "http://localhost:8087/api/eventos/" + eventoId,
                    Object.class
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}