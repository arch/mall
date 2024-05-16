/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import com.mall.crosscut.validation.Flags;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class BrandDto {
    @NotEmpty
    @Schema(description = "品牌名称",required = true)
    private String name;
    @Schema(description = "品牌首字母")
    private String firstLetter;
    @Min(value = 0)
    @Schema(description = "排序字段")
    private Integer sort;
    @Flags(value = {0, 1}, message = "厂家状态不正确")
    @Schema(description = "是否为厂家制造商")
    private Integer factoryStatus;
    @Flags(value = {0, 1}, message = "显示状态不正确")
    @Schema(description = "是否进行显示")
    private Integer showStatus;
    @NotEmpty
    @Schema(description = "品牌logo",required = true)
    private String logo;
    @Schema(description = "品牌大图")
    private String bigPic;
    @Schema(description = "品牌故事")
    private String brandStory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(Integer factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getBrandStory() {
        return brandStory;
    }

    public void setBrandStory(String brandStory) {
        this.brandStory = brandStory;
    }
}