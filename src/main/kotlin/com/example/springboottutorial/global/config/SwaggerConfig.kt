package com.example.springboottutorial.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    val securitySchemeName = "bearerAuth"

    @Bean
    fun v1ApiGroup(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("v1")
        .pathsToMatch("/v1/**")
        .build()

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .components(components())
            .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
            .info(apiInfo())
    }

    private fun components(): Components {
        return Components()
            .addSecuritySchemes(
                securitySchemeName,
                SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )

    }

    private fun apiInfo(): Info {
        return Info()
            .title("Spring Boot Tutorial API")
            .description("Spring Boot Tutorial API")
            .version("v1.0.0")
    }

}