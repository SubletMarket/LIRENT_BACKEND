package com.ssafy.lirent.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        // SecurityScheme 정의 (JWT)
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization")
                .in(SecurityScheme.In.HEADER);

        // 기본적으로 모든 API에 JWT 인증을 요구하도록 설정
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        // OpenAPI 구성: SecurityScheme과 SecurityRequirement 설정
        OpenAPI openAPI = new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .addSecurityItem(securityRequirement)  // 기본적으로 JWT 인증을 모든 API에 추가
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("JWT Swagger Example API")
                        .version("1.0")
                        .description("Example API with JWT Authentication"));

        // /api/member 엔드포인트에 대해서는 JWT 인증을 요구하지 않음
        openAPI.path("/api/member", new PathItem().get(new Operation()));  // 여기서 SecurityRequirement를 설정하지 않음

        return openAPI;
    }
}