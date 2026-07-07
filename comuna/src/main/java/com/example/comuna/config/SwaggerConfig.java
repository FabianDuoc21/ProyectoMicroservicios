package com.example.comuna.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI comunaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio Comuna")
                        .version("1.0")
                        .description("API de Comunas"));
    }

}