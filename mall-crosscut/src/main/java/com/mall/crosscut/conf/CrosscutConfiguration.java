/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.conf;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mall.crosscut.core.ApiExceptionHandler;
import com.mall.crosscut.core.ApiResultProcessorInitializer;
import com.mall.crosscut.core.ExceptionNotifier;
import com.mall.crosscut.filter.CaseInsensitiveRequestFilter;
import com.mall.crosscut.filter.FilterExceptionHandlerFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CrosscutConfiguration implements WebMvcConfigurer, ImportAware {
    private String corsPathPattern;
    private String[] apiResultDisabledURI;
    private String datePattern;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        Map<String, Object> map = importMetadata.getAnnotationAttributes(EnableCrosscut.class.getName());
        if (map == null) {
            return;
        }
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(map);
        corsPathPattern = attributes.getString("corsPathPattern");
        apiResultDisabledURI = attributes.getStringArray("apiResultDisabledURI");
        for (int i = 0; i < apiResultDisabledURI.length; i++) {
           String uri = apiResultDisabledURI[i];
            uri = StringUtils.trimTrailingCharacter(uri, '/');
            apiResultDisabledURI[i] = uri;
        }
        datePattern = attributes.getString("datePattern");
    }

    @Bean
    public FilterRegistrationBean<?> filterExceptionHandlerFilter(ExceptionNotifier exceptionNotifier) {
        FilterRegistrationBean<FilterExceptionHandlerFilter> registration = new FilterRegistrationBean<>();
        FilterExceptionHandlerFilter filter = new FilterExceptionHandlerFilter(exceptionNotifier);
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("FilterExceptionHandlerFilter");
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<?> caseInsensitiveFilter() {
        FilterRegistrationBean<CaseInsensitiveRequestFilter> registration = new FilterRegistrationBean<>();
        CaseInsensitiveRequestFilter filter = new CaseInsensitiveRequestFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("CaseInsensitiveRequestFilter");
        registration.setOrder(Integer.MIN_VALUE + 1);
        return registration;
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(javaTimeModule);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    @ConditionalOnMissingBean
    public ExceptionNotifier exceptionNotifier() {
        return new ExceptionNotifier();
    }

    @Bean
    public ApiExceptionHandler apiExceptionHandler(ExceptionNotifier exceptionNotifier) {
        return new ApiExceptionHandler(exceptionNotifier);
    }

    @Bean
    public ApiResultProcessorInitializer apiResultProcessorInitializer(RequestMappingHandlerAdapter adapter) {
        return new ApiResultProcessorInitializer(adapter, apiResultDisabledURI);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(corsPathPattern)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }
}