package com.example.comuna.webclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class RegionClient {

    private static final Logger logger = LoggerFactory.getLogger(RegionClient.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${region.service.url}")
    private String regionServiceUrl;

    public boolean existeRegion(Integer idRegion) {
        try {
            logger.info("Validando existencia de región con ID: {}", idRegion);

            ResponseEntity<Object> response = restTemplate.getForEntity(
                    regionServiceUrl + "/api/regiones/" + idRegion,
                    Object.class
            );

            boolean existe = response.getStatusCode().is2xxSuccessful();

            logger.info("Resultado validación región {}: {}", idRegion, existe);

            return existe;

        } catch (RestClientException e) {
            logger.warn("No se pudo validar la región {}. Error: {}", idRegion, e.getMessage());
            return false;
        }
    }
}