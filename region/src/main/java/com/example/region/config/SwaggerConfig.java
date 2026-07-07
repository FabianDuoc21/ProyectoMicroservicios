package com.example.region.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI regionOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio Región")
                        .version("1.0")
                        .description("API REST para la gestión de regiones"));
    }
}