/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Represents an utility to notify some exceptions thrown by the specified API, the default implementation
 * is only logging the exceptions. The business systems SHOULD notify by using SMS/IM, such as WeChat.
 */
public class ExceptionNotifier {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Notifying an exception thrown by the specified request, the default implementation is only logging the exception.
     *
     * @param request {@link HttpServletRequest HttpServletRequest}
     * @param cause The exception.
     */
    public void notify(HttpServletRequest request, Throwable cause) {
        logger.error(String.format("HTTP %s: %s throw an exception: %s", request.getMethod(), request.getRequestURI(), cause.getMessage()), cause);
    }

    /**
     * Notifying an exception thrown by the specified request, the default implementation is only logging the exception.
     *
     * @param request {@link HttpServletRequest HttpServletRequest}
     * @param cause The exception.
     * @param promptMessage The better message to prompt the end user
     */
    public void notify(HttpServletRequest request, Throwable cause, String promptMessage) {
        logger.error(String.format("HTTP %s: %s thrown an exception, message: %s exception: %s", request.getMethod(), request.getRequestURI(), promptMessage, cause.getMessage()), cause);
    }
}