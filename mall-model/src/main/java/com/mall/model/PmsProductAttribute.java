/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;

public class PmsProductAttribute implements Serializable {
    private Long id;

    private Long attributeCategoryId;

    private String name;

    private Integer selectType;

    private Integer inputType;

    private String inputList;

    private Integer sort;

    private Integer filterType;

    private Integer searchType;

    private Integer relatedStatus;

    private Integer handAddStatus;

    private Integer type;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public PmsProductAttribute withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttributeCategoryId() {
        return attributeCategoryId;
    }

    public PmsProductAttribute withAttributeCategoryId(Long attributeCategoryId) {
        this.setAttributeCategoryId(attributeCategoryId);
        return this;
    }

    public void setAttributeCategoryId(Long attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public PmsProductAttribute withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSelectType() {
        return selectType;
    }

    public PmsProductAttribute withSelectType(Integer selectType) {
        this.setSelectType(selectType);
        return this;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getInputType() {
        return inputType;
    }

    public PmsProductAttribute withInputType(Integer inputType) {
        this.setInputType(inputType);
        return this;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getInputList() {
        return inputList;
    }

    public PmsProductAttribute withInputList(String inputList) {
        this.setInputList(inputList);
        return this;
    }

    public void setInputList(String inputList) {
        this.inputList = inputList == null ? null : inputList.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public PmsProductAttribute withSort(Integer sort) {
        this.setSort(sort);
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public PmsProductAttribute withFilterType(Integer filterType) {
        this.setFilterType(filterType);
        return this;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public PmsProductAttribute withSearchType(Integer searchType) {
        this.setSearchType(searchType);
        return this;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getRelatedStatus() {
        return relatedStatus;
    }

    public PmsProductAttribute withRelatedStatus(Integer relatedStatus) {
        this.setRelatedStatus(relatedStatus);
        return this;
    }

    public void setRelatedStatus(Integer relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    public Integer getHandAddStatus() {
        return handAddStatus;
    }

    public PmsProductAttribute withHandAddStatus(Integer handAddStatus) {
        this.setHandAddStatus(handAddStatus);
        return this;
    }

    public void setHandAddStatus(Integer handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public Integer getType() {
        return type;
    }

    public PmsProductAttribute withType(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attributeCategoryId=").append(attributeCategoryId);
        sb.append(", name=").append(name);
        sb.append(", selectType=").append(selectType);
        sb.append(", inputType=").append(inputType);
        sb.append(", inputList=").append(inputList);
        sb.append(", sort=").append(sort);
        sb.append(", filterType=").append(filterType);
        sb.append(", searchType=").append(searchType);
        sb.append(", relatedStatus=").append(relatedStatus);
        sb.append(", handAddStatus=").append(handAddStatus);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PmsProductAttribute other = (PmsProductAttribute) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAttributeCategoryId() == null ? other.getAttributeCategoryId() == null : this.getAttributeCategoryId().equals(other.getAttributeCategoryId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSelectType() == null ? other.getSelectType() == null : this.getSelectType().equals(other.getSelectType()))
            && (this.getInputType() == null ? other.getInputType() == null : this.getInputType().equals(other.getInputType()))
            && (this.getInputList() == null ? other.getInputList() == null : this.getInputList().equals(other.getInputList()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getFilterType() == null ? other.getFilterType() == null : this.getFilterType().equals(other.getFilterType()))
            && (this.getSearchType() == null ? other.getSearchType() == null : this.getSearchType().equals(other.getSearchType()))
            && (this.getRelatedStatus() == null ? other.getRelatedStatus() == null : this.getRelatedStatus().equals(other.getRelatedStatus()))
            && (this.getHandAddStatus() == null ? other.getHandAddStatus() == null : this.getHandAddStatus().equals(other.getHandAddStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAttributeCategoryId() == null) ? 0 : getAttributeCategoryId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSelectType() == null) ? 0 : getSelectType().hashCode());
        result = prime * result + ((getInputType() == null) ? 0 : getInputType().hashCode());
        result = prime * result + ((getInputList() == null) ? 0 : getInputList().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getFilterType() == null) ? 0 : getFilterType().hashCode());
        result = prime * result + ((getSearchType() == null) ? 0 : getSearchType().hashCode());
        result = prime * result + ((getRelatedStatus() == null) ? 0 : getRelatedStatus().hashCode());
        result = prime * result + ((getHandAddStatus() == null) ? 0 : getHandAddStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }
}