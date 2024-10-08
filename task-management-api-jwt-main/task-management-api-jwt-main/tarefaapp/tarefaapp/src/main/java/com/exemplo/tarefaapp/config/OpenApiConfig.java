package com.exemplo.tarefaapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Tarefas")
                        .version("1.0.0")
                        .description("Documentação da API para o aplicativo de gerenciamento de tarefas.")
                        .termsOfService("http://exemplo.com/terms")
                        .contact(new Contact()
                                .name("Suporte")
                                .email("suporte@exemplo.com")
                                .url("http://exemplo.com"))
                        .license(new License()
                                .name("Licença Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerJWT"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerJWT", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
