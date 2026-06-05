package com.example.comuna.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RegionClient {

    private final RestTemplate restTemplate;

    public RegionClient() {
        this.restTemplate = new RestTemplate();
    }

    public Boolean existeRegion(Long regionId) {
        try {
            restTemplate.getForObject(
                    "http://localhost:8084/api/regiones/" + regionId,
                    Object.class
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}