/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

import com.mall.crosscut.conf.EnableCrosscut;
import com.mall.jdbc.EnableMyBatis;
import com.mall.security.conf.EnableJwtSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCrosscut(apiResultDisabledURI = { "/v3/api-docs", "/v3/api-docs/swagger-config" }, deployRoot = "deployRoot")
@EnableMyBatis({ "com.sunrise.mall.mapper", "com.sunrise.mall.dao" })
@EnableJwtSecurity(permitAll = {
        "/api-docs",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/api/member/wx/login**",
        "/api/wxpay/notify",
        "/api/product/**",
        "/api/brand/**",
        "/api/home/**"
})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}