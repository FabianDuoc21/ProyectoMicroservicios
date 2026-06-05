package com.ecommerce.ms_pago.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PedidoClient {

    private final RestTemplate restTemplate;

    public PedidoClient() {
        this.restTemplate = new RestTemplate();
    }

    public Boolean existePedido(Long pedidoId) {
        try {
            restTemplate.getForObject(
                    "http://localhost:8081/api/v1/pedidos/" + pedidoId,
                    Object.class
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}