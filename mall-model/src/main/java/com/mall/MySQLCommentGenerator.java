/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;
import java.util.Set;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

public class MySQLCommentGenerator implements CommentGenerator {
    private static final String SUPPORT_SUFFIX = "Support";
    private static final String MAPPER_SUFFIX = "Mapper";
    private static final String API_SCHEMA_CLASS_NAME = "io.swagger.v3.oas.annotations.media.Schema";
    private static final String ADD_SCHEMA_ANNOTATION = "addApiSchema";

    private Properties properties;

    private boolean addRemarkComments;
    private boolean addApiSchema;

    public MySQLCommentGenerator() {
        super();
        properties = new Properties();
        addRemarkComments = false;
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
        addApiSchema = isTrue(properties.getProperty(ADD_SCHEMA_ANNOTATION));
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addRemarkComments(field, introspectedColumn);
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addRemarkComments(method, introspectedColumn);
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addRemarkComments(method, introspectedColumn);
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (addApiSchema && isModel(compilationUnit.getType())) {
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_SCHEMA_CLASS_NAME));
        }
    }

    @Override
    public void addComment(XmlElement xmlElement) {

    }

    @Override
    public void addRootComment(XmlElement rootElement) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        if (addApiSchema && !field.isStatic() && StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            field.addAnnotation(getSchemaAnnotation(introspectedColumn.getRemarks()));
        }
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    private void addRemarkComments(JavaElement javaElement, IntrospectedColumn introspectedColumn) {
        if (addRemarkComments && StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            javaElement.addJavaDocLine("/**");
            String[] remarkLines = introspectedColumn.getRemarks().split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                javaElement.addJavaDocLine(" * " + remarkLine);
            }
            javaElement.addJavaDocLine(" */");
        }
    }

    private String getSchemaAnnotation(String remarks) {
        return "@Schema(description = \"" + remarks + "\")";
    }

    private boolean isModel(FullyQualifiedJavaType type) {
        return (!type.getFullyQualifiedName().contains(MAPPER_SUFFIX) && !type.getFullyQualifiedName().contains(SUPPORT_SUFFIX));
    }
}