package com.PrismaMobi.Prisma_Mobi.infra;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("https://prisma-mobi-production.up.railway.app")))
                .components(new Components().
                addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(
                                SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Prisma-Mobi API")
                        .description("Documentação da API para o sistema PRISMA-MOBI")
                        .contact(new Contact()
                                .name("Cristian Tiago Dutkercz Rosa")
                                .email("dutkercz@gmail.com")
                                .url("https://github.com/Dutkercz")));
    }
}
