package com.panacademy.squad7.bluebank.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:openapi.properties")
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi rootGroup() {
        return GroupedOpenApi
                .builder()
                .group("root")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                    return operation;
                })
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Blue Bank API")
                        .version(appVersion)
                        .description("Blue Bank é uma API de transações bancárias, desenvolvida durante o Bootcamp Pan Academy da Gama em parceria com o banco PAN.")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact().name("Squad 7").url("https://github.com/ramondomiingos/pan-academy-blue-bank"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")
                        ));
    }

}
