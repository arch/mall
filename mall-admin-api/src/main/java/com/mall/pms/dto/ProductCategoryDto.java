/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import com.mall.crosscut.validation.Flags;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ProductCategoryDto {
    @Schema(description = "父分类的编号", required = true)
    private Long parentId;
    @NotEmpty
    @Schema(description = "商品分类名称", required = true)
    private String name;
    @Schema(description = "分类单位")
    private String productUnit;
    @Flags(value = {0, 1}, message = "状态只能为0或1")
    @Schema(description = "是否在导航栏显示", allowableValues = {"0", "1"})
    private Integer navStatus;
    @Flags(value = {0, 1}, message = "状态只能为0或1")
    @Schema(description = "是否进行显示", allowableValues = {"0", "1"})
    private Integer showStatus;
    @Min(value = 0)
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "图标")
    private String icon;
    @Schema(description = "关键字")
    private String keywords;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "产品相关筛选属性集合")
    private List<Long> productAttributeIdList;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(Integer navStatus) {
        this.navStatus = navStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getProductAttributeIdList() {
        return productAttributeIdList;
    }

    public void setProductAttributeIdList(List<Long> productAttributeIdList) {
        this.productAttributeIdList = productAttributeIdList;
    }
}