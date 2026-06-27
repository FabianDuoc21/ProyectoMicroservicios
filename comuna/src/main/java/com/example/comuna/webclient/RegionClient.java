package com.example.comuna.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RegionClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${region.service.url}")
    private String regionServiceUrl;

    public boolean existeRegion(Integer idRegion) {
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(
                    regionServiceUrl + "/api/regiones/" + idRegion,
                    Object.class
            );

            return response.getStatusCode().is2xxSuccessful();

        } catch (Exception e) {
            return false;
        }
    }
}