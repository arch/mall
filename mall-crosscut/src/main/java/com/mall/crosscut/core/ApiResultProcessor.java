/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.core;

import com.mall.crosscut.standard.ApiResult;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * This class will intercept all method's return value and convert that value to {@link ApiResult ApiResult}
 */
public class ApiResultProcessor implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler delegate;
    private final String[] apiResultDisabledURI;

    public ApiResultProcessor(HandlerMethodReturnValueHandler delegate, String[] apiResultDisabledURI) {
        this.delegate = delegate;
        this.apiResultDisabledURI = apiResultDisabledURI;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (isApiResultDisabled(webRequest) || returnType.hasMethodAnnotation(ApiResultDisabled.class)) {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else {
            ApiResult<?> apiResult;
            if(returnValue == null) {
                apiResult = ApiResult.success(null);
            }
            else if(returnValue instanceof ApiResult) {
                apiResult = (ApiResult<?>)returnValue;
            } else {
                apiResult = ApiResult.success(returnValue);
            }

            delegate.handleReturnValue(apiResult, returnType, mavContainer, webRequest);
        }
    }

    private boolean isApiResultDisabled(NativeWebRequest webRequest) {
        if (apiResultDisabledURI == null || apiResultDisabledURI.length == 0) {
            return false;
        }

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            return false;
        }

        String uri = request.getRequestURI();
        uri = StringUtils.trimTrailingCharacter(uri, '/');
        for (String item : apiResultDisabledURI) {
            if (uri.equals(item)) {
                return true;
            }
        }

        return false;
    }
}