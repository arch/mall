/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import static com.mall.mapper.OmsCartItemDynamicSqlSupport.deleteStatus;
import static com.mall.mapper.OmsCartItemDynamicSqlSupport.id;
import static com.mall.mapper.OmsCartItemDynamicSqlSupport.memberId;
import static com.mall.mapper.OmsCartItemDynamicSqlSupport.omsCartItem;
import static com.mall.mapper.OmsCartItemDynamicSqlSupport.productId;
import static com.mall.mapper.OmsCartItemDynamicSqlSupport.productSkuId;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.dao.ProductDao;
import com.mall.domain.CartProduct;
import com.mall.domain.PromotionCartItem;
import com.mall.service.CartItemService;
import com.mall.service.MemberService;
import com.mall.service.PromotionService;
import com.mall.mapper.OmsCartItemMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.mapper.PmsSkuStockMapper;
import com.mall.model.OmsCartItem;
import com.mall.model.PmsProduct;
import com.mall.model.PmsSkuStock;
import com.mall.model.UmsMember;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final OmsCartItemMapper cartItemMapper;
    private final PromotionService promotionService;
    private final MemberService memberService;
    private final ProductDao productDao;
    private final PmsProductMapper productMapper;
    private final PmsSkuStockMapper skuStockMapper;

    public CartItemServiceImpl(OmsCartItemMapper cartItemMapper, PromotionService promotionService,
            MemberService memberService, ProductDao productDao, PmsProductMapper productMapper,
            PmsSkuStockMapper skuStockMapper) {
        this.cartItemMapper = cartItemMapper;
        this.promotionService = promotionService;
        this.memberService = memberService;
        this.productDao = productDao;
        this.productMapper = productMapper;
        this.skuStockMapper = skuStockMapper;
    }

    @Override
    public void add(OmsCartItem cartItem) {
        Optional<PmsProduct> optional = productMapper.selectByPrimaryKey(cartItem.getProductId());
        Assert.ensure(optional.isPresent(), String.format("商品[%d]不存在", cartItem.getProductId()));
        PmsProduct product = optional.get();

        UmsMember currentMember = memberService.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);

        int count;
        Optional<OmsCartItem> exist = getCartItem(cartItem);
        if (exist.isPresent()) {
            OmsCartItem existCartItem = exist.get();
            existCartItem.setModifyDate(LocalDateTime.now());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        } else {
            Optional<PmsSkuStock> stockOptional = skuStockMapper.selectByPrimaryKey(cartItem.getProductSkuId());
            Assert.ensure(stockOptional.isPresent(),  String.format("商品[%d]库存[%d]不足", cartItem.getProductId(), cartItem.getProductSkuId()));
            PmsSkuStock stock = stockOptional.get();

            cartItem.setProductSkuId(stock.getId());
            cartItem.setProductSkuCode(stock.getSkuCode());
            //cartItem.setPrice(product.getPrice());
            cartItem.setProductPic(product.getPic());
            cartItem.setProductName(product.getName());
            cartItem.setProductSubTitle(product.getSubTitle());
            cartItem.setProductSn(product.getProductSn());
            cartItem.setProductBrand(product.getBrandName());
            cartItem.setProductCategoryId(product.getCategoryId());
            cartItem.setCreateDate(LocalDateTime.now());
            count = cartItemMapper.insert(cartItem);
        }

        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public List<OmsCartItem> list() {
        UmsMember currentMember = memberService.getCurrentMember();
        return cartItemMapper.select(c ->
                c.where(memberId, isEqualTo(currentMember.getId()))
                        .and(deleteStatus, isEqualTo(0)));
    }

    @Override
    public List<PromotionCartItem> listPromotion(List<Long> cartIds) {
        List<OmsCartItem> items = list();
        if (!CollectionUtils.isEmpty(cartIds)) {
            items = items.stream().filter(item -> cartIds.contains(item.getId())).collect(Collectors.toList());
        }
        if (items.isEmpty()) {
            return Collections.emptyList();
        }
        return promotionService.calcCartPromotion(items);
    }

    @Override
    public void updateQuantity(long id, int quantity) {
        UmsMember currentMember = memberService.getCurrentMember();
        int count = cartItemMapper.update(c ->
                c.set(omsCartItem.quantity).equalTo(quantity)
                        .where(memberId, isEqualTo(currentMember.getId()))
                        .and(deleteStatus, isEqualTo(0))
                        .and(omsCartItem.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public CartProduct getCartProduct(long productId) {
        return productDao.getCartProduct(productId);
    }

    @Override
    public void updateAttribute(OmsCartItem cartItem) {
        // 删除原购物车信息
        OmsCartItem updateCart = new OmsCartItem();
        updateCart.setId(cartItem.getId());
        updateCart.setModifyDate(LocalDateTime.now());
        updateCart.setDeleteStatus(1);
        cartItemMapper.updateByPrimaryKeySelective(updateCart);

        // 创建一个新的
        cartItem.setId(null);
        this.add(cartItem);
    }

    @Override
    public void delete(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        UmsMember currentMember = memberService.getCurrentMember();
        int count = cartItemMapper.delete(c ->
                c.where(id, isIn(ids))
                        .and(memberId, isEqualTo(currentMember.getId())));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void clear() {
        UmsMember currentMember = memberService.getCurrentMember();
        cartItemMapper.update(c ->
                c.set(deleteStatus).equalTo(1)
                        .where(memberId, isEqualTo(currentMember.getId())));
    }

    private Optional<OmsCartItem> getCartItem(OmsCartItem cartItem) {
        return cartItemMapper.selectOne(c -> {
            AbstractWhereDSL<?> whereDSL =
                    c.where(memberId, isEqualTo(cartItem.getMemberId()))
                    .and(deleteStatus, isEqualTo(0))
                    .and(productId, isEqualTo(cartItem.getProductId()));
            if (cartItem.getProductSkuId() != null) {
                whereDSL.and(productSkuId, isEqualTo(cartItem.getProductSkuId()));
            }
            return c;
        });
    }
}