/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import com.mall.crosscut.excel.annotation.Column;

public class FreightCompany {
    @Column(index = 0)
    private String name;
    @Column(index = 1)
    private String code;
    @Column(index = 2)
    private String kind;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}