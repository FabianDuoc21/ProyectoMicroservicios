package com.ecommerce.ms_pago.webclient;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Hidden
@FeignClient(name = "ms-pedido")
public interface PedidoClient {

    @GetMapping("/api/v1/pedidos/{id}")
    Object existePedido(@PathVariable("id") Long id);
}