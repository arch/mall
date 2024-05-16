/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.controller;

import com.mall.oms.dto.AddressParam;
import com.mall.model.OmsCompanyAddress;
import com.mall.oms.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "收货地址管理")
@RestController
@RequestMapping(value = "/api/oms/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "获取所有收货地址", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<OmsCompanyAddress> get() {
        return addressService.list();
    }

    @Operation(summary = "新建收货地址", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping
    public void post(@RequestBody @Valid AddressParam address) {
        addressService.create(address);
    }

    @Operation(summary = "更新收货地址", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping("/{id}")
    public void put(@PathVariable("id") long id, @RequestBody @Valid AddressParam address) {
        addressService.update(id, address);
    }

    @Operation(summary = "批量删除收货地址", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping
    public void delete(@RequestParam("ids") List<Long> ids) {
        addressService.delete(ids);
    }
}