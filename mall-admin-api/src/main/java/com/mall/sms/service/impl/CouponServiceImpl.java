/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsCouponDynamicSqlSupport.smsCoupon;
import static com.mall.mapper.SmsCouponHistoryDynamicSqlSupport.smsCouponHistory;
import static com.mall.mapper.SmsCouponProductCategoryRelationDynamicSqlSupport.smsCouponProductCategoryRelation;
import static com.mall.mapper.SmsCouponProductRelationDynamicSqlSupport.smsCouponProductRelation;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsCouponHistoryMapper;
import com.mall.mapper.SmsCouponMapper;
import com.mall.mapper.SmsCouponProductCategoryRelationMapper;
import com.mall.mapper.SmsCouponProductRelationMapper;
import com.mall.model.SmsCoupon;
import com.mall.model.SmsCouponHistory;
import com.mall.model.SmsCouponProductCategoryRelation;
import com.mall.model.SmsCouponProductRelation;
import com.mall.sms.dao.CouponDao;
import com.mall.sms.dao.CouponProductCategoryRelationDao;
import com.mall.sms.dao.CouponProductRelationDao;
import com.mall.sms.dto.CouponParam;
import com.mall.sms.service.CouponService;
import java.util.List;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CouponServiceImpl implements CouponService {

    private final SmsCouponMapper couponMapper;
    private final SmsCouponHistoryMapper historyMapper;
    private final SmsCouponProductRelationMapper productRelationMapper;
    private final SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;
    private final CouponProductRelationDao productRelationDao;
    private final CouponProductCategoryRelationDao productCategoryRelationDao;
    private final CouponDao couponDao;

    public CouponServiceImpl(SmsCouponMapper couponMapper,
            SmsCouponProductRelationMapper productRelationMapper,
            SmsCouponProductCategoryRelationMapper productCategoryRelationMapper,
            CouponProductRelationDao productRelationDao, CouponProductCategoryRelationDao productCategoryRelationDao,
            CouponDao couponDao, SmsCouponHistoryMapper historyMapper) {
        this.couponMapper = couponMapper;
        this.productRelationMapper = productRelationMapper;
        this.productCategoryRelationMapper = productCategoryRelationMapper;
        this.productRelationDao = productRelationDao;
        this.productCategoryRelationDao = productCategoryRelationDao;
        this.couponDao = couponDao;
        this.historyMapper = historyMapper;
    }

    @Override
    public void create(CouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);

        //插入优惠券表
        int count = couponMapper.insert(couponParam);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        //插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelations()) {
                productRelation.setCouponId(couponParam.getId());
            }
            count = productRelationDao.insertList(couponParam.getProductRelations());
            Assert.ensure(count == couponParam.getProductRelations().size(), StandardCode.SQL_FAILURE);
        }

        //插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam
                    .getProductCategoryRelations()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            count = productCategoryRelationDao.insertList(couponParam.getProductCategoryRelations());
            Assert.ensure(count == couponParam.getProductCategoryRelations().size(), StandardCode.SQL_FAILURE);
        }
    }

    @Override
    public void delete(Long id) {
        //删除优惠券
        int count = couponMapper.deleteByPrimaryKey(id);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
    }

    @Override
    public void update(long id, CouponParam couponParam) {
        couponParam.setId(id);
        int count = couponMapper.updateByPrimaryKey(couponParam);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
        //删除后插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelations()) {
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);

            count = productRelationDao.insertList(couponParam.getProductRelations());
            Assert.ensure(count == couponParam.getProductRelations().size(), StandardCode.SQL_FAILURE);
        }
        //删除后插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam
                    .getProductCategoryRelations()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            deleteProductCategoryRelation(id);

            count = productCategoryRelationDao.insertList(couponParam.getProductCategoryRelations());
            Assert.ensure(count == couponParam.getProductCategoryRelations().size(), StandardCode.SQL_FAILURE);
        }
    }

    @Override
    public List<SmsCoupon> list(String name, Integer type, int pageIndex, int pageSize) {
        return couponMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = null;
            if (StringUtils.hasText(name)) {
                whereDSL = c.where(smsCoupon.name, isLike("%" + name + "%"));
            }

            if (type != null) {
                if (whereDSL != null) {
                    whereDSL.and(smsCoupon.type, isEqualTo(type));
                } else {
                    c.where(smsCoupon.type, isEqualTo(type));
                }
            }

            c.limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }

    @Override
    public CouponParam get(Long id) {
        return couponDao.selectOne(id);
    }

    @Override
    public List<SmsCouponHistory> history(Long couponId, Integer useStatus, String orderSn, int pageIndex,
            int pageSize) {
        return historyMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = null;
            if (couponId != null) {
                whereDSL = c.where(smsCouponHistory.couponId, isEqualTo(couponId));
            }
            if (useStatus != null) {
                if (whereDSL != null) {
                    whereDSL.and(smsCouponHistory.useStatus, isEqualTo(useStatus));
                } else {
                    c.where(smsCouponHistory.useStatus, isEqualTo(useStatus));
                }
            }
            if (!StringUtils.isEmpty(orderSn)) {
                if (whereDSL != null) {
                    whereDSL.and(smsCouponHistory.orderSn, isEqualTo(orderSn));
                } else {
                    c.where(smsCouponHistory.orderSn, isEqualTo(orderSn));
                }
            }

            c.limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }

    private void deleteProductCategoryRelation(Long couponId) {
        productCategoryRelationMapper
                .delete(c -> c.where(smsCouponProductCategoryRelation.couponId, isEqualTo(couponId)));
    }

    private void deleteProductRelation(Long couponId) {
        productRelationMapper.delete(c -> c.where(smsCouponProductRelation.couponId, isEqualTo(couponId)));
    }
}