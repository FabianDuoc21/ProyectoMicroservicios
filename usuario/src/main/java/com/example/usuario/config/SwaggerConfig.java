package com.example.usuario.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usuarioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio Usuario")
                        .version("1.0")
                        .description("API REST para la gestión de usuarios"));
    }
}
