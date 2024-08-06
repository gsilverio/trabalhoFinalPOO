package com.trabalhoFinalPOO.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Trabalho POO")
                .version("v1")
                .description("API de um mini sistema de adoção de animais de estimação, mais " +
                        "especificadamente em cães e gatos. O usuario podera se cadastrar como " +
                        "quem está disponibilizando a adoção ou por quem quer fazer adoções. A API contem CRUD completo de animais e usuarios.")
                .license(new License().name("Apache 2.0")
                        ));
    }
}
