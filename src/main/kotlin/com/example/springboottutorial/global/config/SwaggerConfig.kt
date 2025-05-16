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

    /**
     * API 그룹화
     */
    @Bean
    fun v1ApiGroup(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("v1")
        .pathsToMatch("/v1/**")
        .build()

    /**
     * Swagger UI 설정
     */
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .components(components())
            .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
            .info(apiInfo())
    }

    /**
     * Swagger UI 에서 사용할 SecurityScheme 설정
     */
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

    /**
     * Swagger UI 에서 사용할 API 정보 설정
     */
    private fun apiInfo(): Info {
        return Info()
            .title("Spring Boot Tutorial API")
            .description("Spring Boot Tutorial API")
            .version("v1.0.0")
    }
}