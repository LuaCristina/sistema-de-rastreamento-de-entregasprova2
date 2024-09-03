package br.edu.iftm.rastreamento.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Documentação da API sobre o Sistema Rastreamento")
                .version("Version 1 (v1)")
                .description("Esta documentação apresenta os endpoints responsáveis pelas funcionalidades de Rastreamento, Endereços e Pacotes.")
                .termsOfService("http://myterms.com")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://mylicenses.com"))
            );
    }
}
