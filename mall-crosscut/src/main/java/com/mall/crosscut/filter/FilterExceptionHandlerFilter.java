/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.filter;

import com.mall.crosscut.core.ExceptionNotifier;
import com.mall.crosscut.standard.ApiResult;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This filter will intercept all exceptions thrown by the following filters, and using
 * {@link ExceptionNotifier ExceptionNotifier} to notify that exceptions.
 * <p>
 * Wait...wait... please tell me why?
 * <p>
 * All requests will first be handled in filters chain, and then entry into container, so if an exception
 * be thrown in filters chain, all the exception handlers for container will have not chance to handle it.
 */
public class FilterExceptionHandlerFilter extends OncePerRequestFilter {
    private List<HttpMessageConverter<?>> messageConverters = getDefaultMessageConverters();

    private final ExceptionNotifier exceptionNotifier;

    public FilterExceptionHandlerFilter(ExceptionNotifier exceptionNotifier) {
        this.exceptionNotifier = exceptionNotifier;
    }

    public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        } catch (Exception exception) {
            String message = String.format("Intercept one exception thrown by filters, message: %s", exception.getMessage());
            exceptionNotifier.notify(request, exception, message);
            writeWithMessageConverters(ApiResult.failure(exception.getMessage()), request, response);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void writeWithMessageConverters(Object returnValue, HttpServletRequest request, HttpServletResponse response) throws IOException, HttpMediaTypeNotAcceptableException {
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }
        MediaType.sortByQualityValue(acceptedMediaTypes);
        Class<?> returnValueType = returnValue.getClass();
        List<MediaType> allSupportedMediaTypes = new ArrayList<>();
        for (MediaType acceptedMediaType : acceptedMediaTypes) {
            for (HttpMessageConverter messageConverter : messageConverters) {
                if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
                    messageConverter.write(returnValue, acceptedMediaType, outputMessage);
                    return;
                }
            }
        }
        for (HttpMessageConverter messageConverter : messageConverters) {
            allSupportedMediaTypes.addAll(messageConverter.getSupportedMediaTypes());
        }
        throw new HttpMediaTypeNotAcceptableException(allSupportedMediaTypes);
    }

    private List<HttpMessageConverter<?>> getDefaultMessageConverters() {
        List<HttpMessageConverter<?>> result = new ArrayList<>();
        result.addAll(new RestTemplate().getMessageConverters());
        return result;
    }
}