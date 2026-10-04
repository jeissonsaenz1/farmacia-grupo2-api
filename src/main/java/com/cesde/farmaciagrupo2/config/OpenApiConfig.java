package com.cesde.farmaciagrupo2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Farmacia Grupo 2 API")
                        .version("1.0.0")
                        .description("API REST para la gestión de medicamentos, laboratorios, inventario, fórmulas y ventas.")
                        .contact(new Contact()
                                .name("Grupo 2")
                                .email("farmaciagrupo2@ejemplo.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}