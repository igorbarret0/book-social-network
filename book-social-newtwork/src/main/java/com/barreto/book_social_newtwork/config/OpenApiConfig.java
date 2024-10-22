package com.barreto.book_social_newtwork.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityScheme;


import java.util.Arrays;

@Configuration

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Api que simula uma rede social de livros")
                        .version("1.0")
                        .description("Simula uma rede social de livros")
                        .contact(new Contact()
                                .email("Equipe de desenvolvimento")
                                .email("email@example.com")
                                .url("https://www.example.com")
                        )
                        .license(new License()
                                .name("Licenca de uso")
                                .url("https://www.example.com/license")
                        )
                )
                .servers(Arrays.asList(
                        new Server().url("http://localhost:8888/api/v1/").description("Servidor Local")))
                .addTagsItem(new Tag().name("Books").description("Operacoes relacionadas a livros"))
                .addTagsItem(new Tag().name("Authentication").description("Operacoes relacionadas a autenticacao"))
                .addTagsItem(new Tag().name("Feedbacks").description("Operacoes relacionadas a feedbacks"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

}
