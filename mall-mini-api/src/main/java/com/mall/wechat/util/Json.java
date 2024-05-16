/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.util.ObjectUtils;

public final class Json {
    private static volatile ObjectMapper objectMapper;

    private Json() {
    }

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            synchronized (com.mall.crosscut.util.Json.class) {
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();

                    JavaTimeModule javaTimeModule = new JavaTimeModule();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
                    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));

                    objectMapper.registerModule(javaTimeModule);
                    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
                    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
                }
            }
        }

        return objectMapper;
    }

    public static <T> T deserialize(String json, Class<T> type) {
        if (ObjectUtils.isEmpty(json)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(json, type);
        } catch (Throwable cause) {
            throw new IllegalArgumentException(String.format("deserialize json failure: %s", cause.getMessage()), cause);
        }
    }

    public static <T> T deserialize(String json, TypeReference<T> type) {
        if (ObjectUtils.isEmpty(json)) {
            return null;
        }

        try {
            return getObjectMapper().readValue(json, type);
        } catch (Throwable cause) {
            throw new IllegalArgumentException(String.format("deserialize json failure: %s", cause.getMessage()), cause);
        }
    }

    public static String serialize(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }

        try {
            return getObjectMapper().writeValueAsString(value);
        } catch (Throwable cause) {
            throw new IllegalArgumentException(String.format("deserialize json failure: %s", cause.getMessage()),
                    cause);
        }
    }

    public static <T> T convert(Object object, Class<T> clazz) {
        if (ObjectUtils.isEmpty(object)) {
            return null;
        }

        return getObjectMapper().convertValue(object, clazz);
    }
}