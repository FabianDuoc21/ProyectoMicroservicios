package com.ecommerce.ms_pedido.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteClient {

    private final RestTemplate restTemplate;

    public ClienteClient() {
        this.restTemplate = new RestTemplate();
    }

    public Boolean existeCliente(Long clienteId) {
        try {
            restTemplate.getForObject(
                    "http://localhost:8080/api/v1/clientes/" + clienteId,
                    Object.class
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}