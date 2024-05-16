/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.lang.NonNull;

public class CrossConstraintBeanFactoryPostProcessor implements BeanFactoryPostProcessor, ApplicationContextAware {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final String[] basePackages;

    public CrossConstraintBeanFactoryPostProcessor(String... basePackages) {
        this.basePackages = basePackages;
    }

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof BeanDefinitionRegistry) {
            try {
                BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
                ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
                scanner.setResourceLoader(applicationContext);
                scanner.addIncludeFilter(new AssignableTypeFilter(AbstractChecker.class));
                scanner.scan(basePackages);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}