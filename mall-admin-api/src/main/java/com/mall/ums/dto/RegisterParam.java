/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterParam {
    @NotEmpty
    @Schema(description =  "用户名", required = true)
    private String username;
    @NotEmpty
    @Schema(description =  "密码", required = true)
    private String password;
    @Schema(description =  "用户头像")
    private String icon;
    @Email
    @Schema(description =  "邮箱")
    private String email;
    @Schema(description =  "用户昵称")
    private String nickName;
    @Schema(description =  "备注")
    private String note;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}