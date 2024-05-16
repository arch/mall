/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.model.UmsMemberReceiveAddress;
import com.mall.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "会员收货地址管理")
@RestController
@RequestMapping(value = "/api/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "添加收货地址", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping
    public void create(@RequestBody @Valid UmsMemberReceiveAddress address) {
        addressService.create(address);
    }

    @Operation(summary = "删除收货地址", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        addressService.delete(id);
    }

    @Operation(summary = "修改收货地址", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/{id}")
    public void update(@PathVariable long id, @RequestBody @Valid UmsMemberReceiveAddress address) {
        addressService.update(id, address);
    }

    @Operation(summary = "获取收货地址详情", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/{id}")
    public UmsMemberReceiveAddress get(@PathVariable long id) {
        return addressService.get(id);
    }

    @Operation(summary = "显示所有收货地址", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping
    public List<UmsMemberReceiveAddress> get() {
        return addressService.list();
    }
}