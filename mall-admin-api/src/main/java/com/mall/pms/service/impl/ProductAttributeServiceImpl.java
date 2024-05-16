/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service.impl;

import static com.mall.mapper.PmsProductAttributeDynamicSqlSupport.pmsProductAttribute;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import com.mall.pms.dao.ProductAttributeDao;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.PmsProductAttributeCategoryMapper;
import com.mall.mapper.PmsProductAttributeMapper;
import com.mall.model.PmsProductAttribute;
import com.mall.model.PmsProductAttributeCategory;
import com.mall.pms.dto.ProductAttribute;
import com.mall.pms.dto.ProductAttributeDto;
import com.mall.pms.service.ProductAttributeService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {
    private final PmsProductAttributeMapper productAttributeMapper;
    private final PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    private final ProductAttributeDao productAttributeDao;

    public ProductAttributeServiceImpl(PmsProductAttributeMapper productAttributeMapper,
                                       PmsProductAttributeCategoryMapper productAttributeCategoryMapper,
                                       ProductAttributeDao productAttributeDao) {
        this.productAttributeMapper = productAttributeMapper;
        this.productAttributeCategoryMapper = productAttributeCategoryMapper;
        this.productAttributeDao = productAttributeDao;
    }

    @Override
    public List<PmsProductAttribute> getList(Long categoryId, Integer type, int pageIndex, int pageSize) {
        return productAttributeMapper.select(c ->
                c.where(pmsProductAttribute.attributeCategoryId, isEqualTo(categoryId))
                        .and(pmsProductAttribute.type, isEqualTo(type))
                        .orderBy(pmsProductAttribute.sort.descending()));
    }

    @Override
    public void create(ProductAttributeDto productAttributeDto) {
        // 新增商品属性以后需要更新商品属性分类数量
        var optional = productAttributeCategoryMapper.selectByPrimaryKey(productAttributeDto.getAttributeCategoryId());
        if (optional.isEmpty()) {
            Assert.failure(
                    StandardCode.BAD_REQUEST.withMessage("商品属性分类[" + productAttributeDto.getAttributeCategoryId() + "]不存在"));
        }

        PmsProductAttribute productAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(productAttributeDto, productAttribute);
        int count = productAttributeMapper.insertSelective(productAttribute);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        PmsProductAttributeCategory category = optional.get();
        if (productAttribute.getType() == 0) {
            category.setAttributeCount(category.getAttributeCount() + 1);
        } else if (productAttribute.getType() == 1) {
            category.setParamCount(category.getParamCount() + 1);
        }
        count = productAttributeCategoryMapper.updateByPrimaryKey(category);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(Long id, ProductAttributeDto productAttributeDto) {
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        productAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeDto, productAttribute);
        int count = productAttributeMapper.updateByPrimaryKeySelective(productAttribute);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public PmsProductAttribute get(long id) {
        return productAttributeMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public void delete(List<Long> ids) {
        if (ids.isEmpty())
            return;

        // 获取分类
        var optional = productAttributeMapper.selectByPrimaryKey(ids.get(0));
        if (optional.isEmpty()) {
            Assert.failure(StandardCode.BAD_REQUEST.withMessage("无效ID"));
        }
        PmsProductAttribute productAttribute = optional.get();
        Integer type = productAttribute.getType();
        var optionalCategory = productAttributeCategoryMapper.selectByPrimaryKey(productAttribute.getAttributeCategoryId());
        if (optionalCategory.isEmpty()) {
            Assert.failure(StandardCode.BAD_REQUEST.withMessage("脏数据，找不到属性分类"));
        }

        PmsProductAttributeCategory category = optionalCategory.get();

        int count = productAttributeMapper.delete(c -> c.where(pmsProductAttribute.id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);

        // 删除完成后修改数量
        if (type == 0) {
            if (category.getAttributeCount() >= count) {
                category.setAttributeCount(category.getAttributeCount() - count);
            } else {
                category.setAttributeCount(0);
            }
        } else if (type == 1) {
            if (category.getParamCount() >= count) {
                category.setParamCount(category.getParamCount() - count);
            } else {
                category.setParamCount(0);
            }
        }
        count = productAttributeCategoryMapper.updateByPrimaryKey(category);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public List<ProductAttribute> getAttributes(long productCategoryId) {
        return productAttributeDao.getAttributes(productCategoryId);
    }
}