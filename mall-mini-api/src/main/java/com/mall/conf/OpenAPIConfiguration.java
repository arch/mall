/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.conf;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("MALL小程序")
                .description("MALL小程序相关接口文档")
                .version("v1.0"))
                .components(new Components()
                        .addSecuritySchemes("jwtScheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .name("Authorization")
                                        .description("JWT Bearer Authorization")
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("http")
                                        .bearerFormat("bearer")));
    }
}