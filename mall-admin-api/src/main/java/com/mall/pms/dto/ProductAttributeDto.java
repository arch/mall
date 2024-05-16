/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import com.mall.crosscut.validation.Flags;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

public class ProductAttributeDto {
    @NotEmpty
    @Schema(description = "属性分类ID")
    private long attributeCategoryId;
    @NotEmpty
    @Schema(description = "属性名称")
    private String name;
    @Flags({0, 1, 2})
    @Schema(description = "属性选择类型：0->唯一；1->单选；2->多选")
    private int selectType;
    @Flags({0, 1})
    @Schema(description = "属性录入方式：0->手工录入；1->从列表中选取")
    private int inputType;
    @Schema(description = "可选值列表，以逗号隔开")
    private String inputList;
    private int sort;
    @Schema(description = "分类筛选样式：0->普通；1->颜色")
    @Flags({0, 1})
    private int filterType;
    @Schema(description = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    @Flags({0, 1, 2})
    private int searchType;
    @Schema(description = "相同属性产品是否关联；0->不关联；1->关联")
    @Flags({0, 1})
    private int relatedStatus;
    @Schema(description = "是否支持手动新增；0->不支持；1->支持")
    @Flags({0, 1})
    private int handAddStatus;
    @Schema(description = "属性的类型；0->规格；1->参数")
    @Flags({0, 1})
    private int type;

    public Long getAttributeCategoryId() {
        return attributeCategoryId;
    }

    public void setAttributeCategoryId(Long attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getInputList() {
        return inputList;
    }

    public void setInputList(String inputList) {
        this.inputList = inputList;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getRelatedStatus() {
        return relatedStatus;
    }

    public void setRelatedStatus(Integer relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    public Integer getHandAddStatus() {
        return handAddStatus;
    }

    public void setHandAddStatus(Integer handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}