package com.proyecto.pasteleria.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pasteleriaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Pastelería Artesanal")
                        .description("Sistema de gestión de pedidos y productos")
                        .version("1.0"));
    }
}
