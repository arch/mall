/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
@Import({JdbcMyBatisConfiguration.class})
@Configuration
@MapperScan
public @interface EnableMyBatis {
    /**
     * Alias for {@link MapperScan#value}.
     */
    @AliasFor(annotation = MapperScan.class)
    String[] value() default {};

    /**
     * Alias for {@link MapperScan#basePackages}.
     */
    @AliasFor(annotation = MapperScan.class)
    String[] basePackages() default {};

    /**
     * Alias for {@link MapperScan#basePackageClasses}.
     */
    @AliasFor(annotation = MapperScan.class)
    Class<?>[] basePackageClasses() default {};

    /**
     * Alias for {@link MapperScan#nameGenerator}.
     */
    @AliasFor(annotation = MapperScan.class)
    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

    /**
     * Alias for {@link MapperScan#annotationClass}.
     */
    @AliasFor(annotation = MapperScan.class)
    Class<? extends Annotation> annotationClass() default Annotation.class;

    /**
     * Alias for {@link MapperScan#markerInterface}.
     */
    @AliasFor(annotation = MapperScan.class)
    Class<?> markerInterface() default Class.class;

    /**
     * Alias for {@link MapperScan#sqlSessionTemplateRef}.
     */
    @AliasFor(annotation = MapperScan.class)
    String sqlSessionTemplateRef() default "";

    /**
     * Alias for {@link MapperScan#sqlSessionFactoryRef}.
     */
    @AliasFor(annotation = MapperScan.class)
    String sqlSessionFactoryRef() default "";

    /**
     * Alias for {@link MapperScan#factoryBean}.
     */
    @AliasFor(annotation = MapperScan.class)
    Class<? extends MapperFactoryBean> factoryBean() default MapperFactoryBean.class;

    /**
     * Alias for {@link MapperScan#lazyInitialization}.
     */
    @AliasFor(annotation = MapperScan.class)
    String lazyInitialization() default "";
}