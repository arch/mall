/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Json {
    private static volatile ObjectMapper objectMapper;

    private Json() {
    }

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            synchronized (Json.class) {
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
                    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
                }
            }
        }

        return objectMapper;
    }

    public static <T> T deserialize(String json, Class<T> type) {
        try {
            return getObjectMapper().readValue(json, type);
        } catch (Throwable cause) {
            throw new IllegalArgumentException(String.format("deserialize json failure: %s", cause.getMessage()), cause);
        }
    }

    public static <T> T deserialize(String json, TypeReference<T> type) {
        try {
            return getObjectMapper().readValue(json, type);
        } catch (Throwable cause) {
            throw new IllegalArgumentException(String.format("deserialize json failure: %s", cause.getMessage()), cause);
        }
    }

    public static String serialize(Object value) {
        try {
            return getObjectMapper().writeValueAsString(value);
        } catch (Throwable cause) {
            throw new IllegalArgumentException(String.format("deserialize json failure: %s", cause.getMessage()),
                    cause);
        }
    }

    public static <T> T convert(Object object, Class<T> clazz) {
        if (object == null) {
            return null;
        }
        return getObjectMapper().convertValue(object, clazz);
    }
}