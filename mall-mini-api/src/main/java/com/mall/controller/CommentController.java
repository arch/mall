/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.domain.CommentParam;
import com.mall.model.PmsComment;
import com.mall.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "商品评论管理")
@RestController
@RequestMapping(value = "/api/comment", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "获取评论列表")
    @GetMapping(value = "/{productId}")
    public List<PmsComment> get(@PathVariable long productId,
            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return commentService.list(productId, pageIndex, pageSize);
    }

    @Operation(summary = "新建评论", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping
    public void post(@RequestBody CommentParam commentParam) {
        commentService.create(commentParam);
    }
}