/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import static com.mall.mapper.SmsCouponDynamicSqlSupport.smsCoupon;
import static com.mall.mapper.SmsCouponHistoryDynamicSqlSupport.smsCouponHistory;
import static com.mall.mapper.SmsCouponProductCategoryRelationDynamicSqlSupport.smsCouponProductCategoryRelation;
import static com.mall.mapper.SmsCouponProductRelationDynamicSqlSupport.smsCouponProductRelation;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isGreaterThan;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLessThan;
import static org.mybatis.dynamic.sql.SqlBuilder.isNotEqualTo;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.dao.CouponHistoryDao;
import com.mall.domain.CouponHistoryDetail;
import com.mall.domain.PromotionCartItem;
import com.mall.service.CouponService;
import com.mall.service.MemberService;
import com.mall.mapper.PmsProductMapper;
import com.mall.mapper.SmsCouponHistoryMapper;
import com.mall.mapper.SmsCouponMapper;
import com.mall.mapper.SmsCouponProductCategoryRelationMapper;
import com.mall.mapper.SmsCouponProductRelationMapper;
import com.mall.model.PmsProduct;
import com.mall.model.SmsCoupon;
import com.mall.model.SmsCouponHistory;
import com.mall.model.SmsCouponProductCategoryRelation;
import com.mall.model.SmsCouponProductRelation;
import com.mall.model.UmsMember;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {
    private final MemberService memberService;
    private final SmsCouponMapper couponMapper;
    private final SmsCouponHistoryMapper couponHistoryMapper;
    private final CouponHistoryDao couponHistoryDao;
    private final SmsCouponProductRelationMapper couponProductRelationMapper;
    private final SmsCouponProductCategoryRelationMapper couponProductCategoryRelationMapper;
    private final PmsProductMapper productMapper;

    public CouponServiceImpl(MemberService memberService,
                             SmsCouponMapper couponMapper,
                             SmsCouponHistoryMapper couponHistoryMapper,
                             CouponHistoryDao couponHistoryDao,
                             SmsCouponProductRelationMapper couponProductRelationMapper,
                             SmsCouponProductCategoryRelationMapper couponProductCategoryRelationMapper,
                             PmsProductMapper productMapper) {
        this.memberService = memberService;
        this.couponMapper = couponMapper;
        this.couponHistoryMapper = couponHistoryMapper;
        this.couponHistoryDao = couponHistoryDao;
        this.couponProductRelationMapper = couponProductRelationMapper;
        this.couponProductCategoryRelationMapper = couponProductCategoryRelationMapper;
        this.productMapper = productMapper;
    }

    @Override
    public void add(long couponId) {
        // 获取优惠券信息
        Optional<SmsCoupon> optional = couponMapper.selectByPrimaryKey(couponId);

        // ensure exist
        Assert.ensure(optional.isPresent(), StandardCode.BAD_REQUEST.withMessage("优惠券不存在"));

        SmsCoupon coupon = optional.get();

        // 判断数量
        Assert.ensure(coupon.getCount() > 0,  StandardCode.BAD_REQUEST.withMessage("优惠券已经领完了"));

        // 判断时间
        Assert.ensure(coupon.getEnableTime() == null || LocalDateTime.now().isAfter(coupon.getEnableTime()), StandardCode.BAD_REQUEST.withMessage("优惠券还没到领取时间"));

        UmsMember currentMember = memberService.getCurrentMember();

        // 判断用户领取的优惠券数量是否超过限制
        long count = couponHistoryMapper.count(c ->
                c.where(smsCouponHistory.couponId, isEqualTo(couponId))
                        .and(smsCouponHistory.memberId, isEqualTo(currentMember.getId())));
        Assert.ensure(count < coupon.getPerLimit(),  StandardCode.BAD_REQUEST.withMessage("您已经领取过该优惠券"));

        // 生成领取优惠券历史
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        couponHistory.setCouponId(couponId);
        couponHistory.setCouponCode(generateCouponCode(currentMember.getId()));
        couponHistory.setCreateTime(LocalDateTime.now());
        couponHistory.setMemberId(currentMember.getId());
        couponHistory.setMemberNickname(currentMember.getNickname());
        // 主动领取
        couponHistory.setGetType(1);
        // 未使用
        couponHistory.setUseStatus(0);
        int updateCounts = couponHistoryMapper.insert(couponHistory);
        Assert.ensure(updateCounts == 1, StandardCode.SQL_FAILURE);

        // 修改优惠券表的数量、领取数量
        coupon.setCount(coupon.getCount() - 1);
        coupon.setReceiveCount(coupon.getReceiveCount() == null ? 1 : coupon.getReceiveCount() + 1);
        updateCounts = couponMapper.updateByPrimaryKey(coupon);
        Assert.ensure(updateCounts == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public List<SmsCouponHistory> history(Integer useStatus) {
        UmsMember currentMember = memberService.getCurrentMember();
        return couponHistoryMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = c.where(smsCouponHistory.memberId, isEqualTo(currentMember.getId()));
            if (useStatus != null) {
                whereDSL.and(smsCouponHistory.useStatus, isEqualTo(useStatus));
            }
            return c;
        });
    }

    @Override
    public List<SmsCoupon> list(Integer useStatus) {
        UmsMember member = memberService.getCurrentMember();
        return couponHistoryDao.getCouponList(member.getId(), useStatus);
    }

    @Override
    public List<CouponHistoryDetail> listCart(List<PromotionCartItem> promotionCartItems, int type) {
        UmsMember currentMember = memberService.getCurrentMember();
        // 获取该用户所有优惠券
        List<CouponHistoryDetail> allList = couponHistoryDao.getDetailList(currentMember.getId());
        // 根据优惠券使用类型来判断优惠券是否可用
        List<CouponHistoryDetail> enableList = new ArrayList<>();
        List<CouponHistoryDetail> disableList = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        for (CouponHistoryDetail couponHistoryDetail : allList) {
            SmsCoupon coupon = couponHistoryDetail.getCoupon();
            if (coupon == null) {
                continue;
            }
            Integer useType = coupon.getUseType();
            BigDecimal minPoint = coupon.getMinPoint();
            LocalDateTime endTime = coupon.getEndTime();
            if (useType.equals(0)) {
                // 0->全场通用
                // 判断是否满足优惠起点
                // 计算购物车商品的总价
                BigDecimal totalAmount = calcTotalAmount(promotionCartItems);
                if (now.isBefore(endTime) && totalAmount.subtract(minPoint).intValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            } else if (useType.equals(1)) {
                // 1->指定分类
                // 计算指定分类商品的总价
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelation categoryRelation : couponHistoryDetail.getCategoryRelations()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(promotionCartItems, productCategoryIds);
                if (now.isBefore(endTime) && totalAmount.intValue() > 0 && totalAmount.subtract(minPoint).intValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            } else if (useType.equals(2)) {
                // 2->指定商品
                // 计算指定商品的总价
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelations()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(promotionCartItems, productIds);
                if (now.isBefore(endTime) && totalAmount.intValue() > 0 && totalAmount.subtract(minPoint).intValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            }
        }

        if (type == 1) {
            return enableList;
        } else {
            return disableList;
        }
    }

    @Override
    public List<SmsCoupon> listByProduct(Long productId) {
        List<Long> allCouponIds = new ArrayList<>();

        // 获取指定商品优惠券
        List<SmsCouponProductRelation> cprList = couponProductRelationMapper.select(c ->
                c.where(smsCouponProductRelation.productId, isEqualTo(productId)));
        if(cprList != null && !cprList.isEmpty()){
            List<Long> couponIds = cprList.stream().map(SmsCouponProductRelation::getCouponId).collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }

        // 获取指定分类优惠券
        Optional<PmsProduct> optional = productMapper.selectByPrimaryKey(productId);
        Assert.ensure(optional.isPresent(), StandardCode.BAD_REQUEST.withMessage(String.format("指定的商品: [%d]不存在", productId)));

        PmsProduct product = optional.get();
        List<SmsCouponProductCategoryRelation> cpcrList = couponProductCategoryRelationMapper.select(c ->
                c.where(smsCouponProductCategoryRelation.productCategoryId, isEqualTo(product.getCategoryId())));
        if(cpcrList != null && !cpcrList.isEmpty()){
            List<Long> couponIds = cpcrList.stream().map(SmsCouponProductCategoryRelation::getCouponId).collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }

        if(allCouponIds.isEmpty()){
            return Collections.emptyList();
        }

        // 所有优惠券
        return couponMapper.select(c -> {
            c.where(smsCoupon.endTime, isGreaterThan(LocalDateTime.now()))
                    .and(smsCoupon.startTime, isLessThan(LocalDateTime.now()))
                    .and(smsCoupon.useType, isEqualTo(0))
                    .or(smsCoupon.endTime, isGreaterThan(LocalDateTime.now()))
                    .and(smsCoupon.startTime, isLessThan(LocalDateTime.now()))
                    .and(smsCoupon.useType, isNotEqualTo(0))
                    .and(smsCoupon.id, isIn(allCouponIds));
            return c;
        });
    }

    /**
     * 16位优惠码生成：时间戳后8位+4位随机数+用户id后4位
     */
    private String generateCouponCode(Long memberId) {
        StringBuilder sb = new StringBuilder();
        long currentTimeMillis = System.currentTimeMillis();
        String timeMillisStr = Long.toString(currentTimeMillis);
        sb.append(timeMillisStr.substring(timeMillisStr.length() - 8));
        for (int i = 0; i < 4; i++) {
            sb.append(new Random().nextInt(10));
        }
        String memberIdStr = memberId.toString();
        if (memberIdStr.length() <= 4) {
            sb.append(String.format("%04d", memberId));
        } else {
            sb.append(memberIdStr.substring(memberIdStr.length() - 4));
        }
        return sb.toString();
    }

    private BigDecimal calcTotalAmount(List<PromotionCartItem> promotionCartItems) {
        BigDecimal total = new BigDecimal("0");
        for (PromotionCartItem item : promotionCartItems) {
            BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
            total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    private BigDecimal calcTotalAmountByproductCategoryId(List<PromotionCartItem> promotionCartItems, List<Long> productCategoryIds) {
        BigDecimal total = new BigDecimal("0");
        for (PromotionCartItem item : promotionCartItems) {
            if (productCategoryIds.contains(item.getProductCategoryId())) {
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calcTotalAmountByProductId(List<PromotionCartItem> promotionCartItems, List<Long> productIds) {
        BigDecimal total = new BigDecimal("0");
        for (PromotionCartItem item : promotionCartItems) {
            if (productIds.contains(item.getProductId())) {
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }
}