/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mall.crosscut.standard.ApiResult;
import com.mall.crosscut.util.Json;
import com.mall.domain.MemberLoginDto;
import com.mall.security.core.Jwt;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

public abstract class ControllerTest {
    private static final int SUCCESS_CODE = 200;
    private static Jwt jwt;

    @BeforeAll
    static void setup(@Autowired MockMvc mvc) throws Exception {
        MemberLoginDto param = new MemberLoginDto();
        param.setCode("code");
        MvcResult mvcResult = mvc.perform(post("/api/member/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json.serialize(param)))
                .andExpect(status().isOk()).andReturn();
        jwt = apiResult(mvcResult, new TypeReference<>() { });
        assertNotNull(jwt);
    }

    protected static String bearerHeader() {
        return String.format("Bearer %s", jwt.getToken());
    }

    protected static <T> T apiResult(MvcResult mvcResult, TypeReference<ApiResult<T>> typeReference) throws IOException {
        String json = mvcResult.getResponse().getContentAsString();
        ApiResult<T> apiResult = Json.deserialize(json, typeReference);
        assertNotNull(apiResult);
        assertEquals(apiResult.getCode(), SUCCESS_CODE);
        return apiResult.getData();
    }
}