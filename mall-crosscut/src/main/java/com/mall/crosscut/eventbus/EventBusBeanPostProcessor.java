/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.eventbus;

import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionException;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.function.BiConsumer;

@SuppressWarnings("UnstableApiUsage")
public class EventBusBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final SpelExpressionParser expressionParser = new SpelExpressionParser();

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        this.process(bean, EventBus::unregister, "destruction");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        this.process(bean, EventBus::register, "initialization");
        return bean;
    }

    private void process(Object bean, BiConsumer<EventBus, Object> consumer, String action) {
        Object proxy = this.getTargetObject(bean);
        Subscriber annotation = AnnotationUtils.getAnnotation(proxy.getClass(), Subscriber.class);
        if (annotation == null) {
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("{}: processing bean of type {} during {}", this.getClass().getSimpleName(),
                    proxy.getClass().getName(), action);
        }

        String annotationValue = annotation.value();
        try {
            Expression expression = expressionParser.parseExpression(annotationValue);
            Object value = expression.getValue();
            if (!(value instanceof EventBus)) {
                logger.error("{}: expression {} did not evaluate to an instance of EventBus for bean of type {}",
                        this.getClass().getSimpleName(), annotationValue, proxy.getClass().getSimpleName());
                return;
            }
            EventBus eventBus = (EventBus) value;
            consumer.accept(eventBus, proxy);
        } catch (ExpressionException ex) {
            logger.error("{}: unable to parse/evaluate expression {} for bean of type {}",
                    this.getClass().getSimpleName(), annotationValue, proxy.getClass().getName());
        }
    }

    private Object getTargetObject(Object proxy) throws BeansException {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            try {
                return ((Advised) proxy).getTargetSource().getTarget();
            } catch (Exception e) {
                throw new FatalBeanException("Error getting target of JDK proxy", e);
            }
        }
        return proxy;
    }
}