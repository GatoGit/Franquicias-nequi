package com.nequi.franquicias.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // Configuracion del Swagger
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Franquicias")
                        .version("1.0.0")
                        .description("Documentación de la API para gestión de franquicias de Nequi, sucursales y productos.")
                        .contact(new Contact()
                                .name("Santiago Soto Vallejo")
                                .email("sant4562@gmail.com")
                                .url("https://www.linkedin.com/in/ssotov2/"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}
