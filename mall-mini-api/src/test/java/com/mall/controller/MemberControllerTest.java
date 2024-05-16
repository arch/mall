/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mall.ControllerTest;
import com.mall.model.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "scheduler.cancel-timeout-order-cron=-")
@AutoConfigureMockMvc
class MemberControllerTest extends ControllerTest {
    @Test
    void me(@Autowired MockMvc mvc) throws Exception {
        MvcResult mvcResult = mvc.perform(get("/api/member/me")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, bearerHeader()))
                .andExpect(status().isOk()).andReturn();
        UmsMember member = apiResult(mvcResult, new TypeReference<>() {
        });
        assertNotNull(member);

        assertEquals("test", member.getUsername());
    }
}