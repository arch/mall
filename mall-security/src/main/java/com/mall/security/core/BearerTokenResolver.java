/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.security.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BearerTokenResolver implements TokenResolver {
    private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+=/]+)=*$");
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String resolve(HttpServletRequest request) {
        String authorizationHeaderToken = resolveFromAuthorizationHeader(request);
        String parameterToken = resolveFromRequestParameters(request);
        if (authorizationHeaderToken != null) {
            if (parameterToken != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("invalid_token: Found multiple bearer tokens in the request, https://tools.ietf.org/html/rfc6750#section-3.1");
                }
            }
            return authorizationHeaderToken;
        }
        else return parameterToken;
    }

    private String resolveFromRequestParameters(HttpServletRequest request) {
        String[] values = request.getParameterValues("access_token");
        if (values == null || values.length == 0)  {
            return null;
        }

        if (values.length == 1) {
            return values[0];
        }

        if (logger.isDebugEnabled()) {
            logger.debug("invalid_token: Found multiple bearer tokens in the request, https://tools.ietf.org/html/rfc6750#section-3.1");
        }

        return null;
    }

    private String resolveFromAuthorizationHeader(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer")) {
            Matcher matcher = authorizationPattern.matcher(authorization);

            if (!matcher.matches()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("invalid_token: Bearer token is malformed, https://tools.ietf.org/html/rfc6750#section-3.1");
                }

                return null;
            }

            return matcher.group("token");
        }
        return null;
    }
}