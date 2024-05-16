/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service.impl;

import com.mall.pms.dao.ProductAttributeDao;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.PmsProductAttributeCategoryMapper;
import com.mall.model.PmsProductAttributeCategory;
import com.mall.pms.dto.ProductAttributeCategoryDto;
import com.mall.pms.service.ProductAttributeCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mall.mapper.PmsProductAttributeCategoryDynamicSqlSupport.pmsProductAttributeCategory;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class ProductAttributeCategoryServiceImpl implements ProductAttributeCategoryService {
    private final PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    private final ProductAttributeDao productAttributeDao;

    public ProductAttributeCategoryServiceImpl(PmsProductAttributeCategoryMapper productAttributeCategoryMapper,
                                               ProductAttributeDao productAttributeDao) {
        this.productAttributeCategoryMapper = productAttributeCategoryMapper;
        this.productAttributeDao = productAttributeDao;
    }

    @Override
    public void create(String name) {
        var category = new PmsProductAttributeCategory().withName(name);
        int count = productAttributeCategoryMapper.insertSelective(category);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(Long id, String name) {
        int count = productAttributeCategoryMapper.update(c ->
                c.set(pmsProductAttributeCategory.name).equalTo(name)
                .where(pmsProductAttributeCategory.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(Long id) {
        int count = productAttributeCategoryMapper.deleteByPrimaryKey(id);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public PmsProductAttributeCategory get(Long id) {
        return productAttributeCategoryMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public List<PmsProductAttributeCategory> getList(int pageIndex, int pageSize) {
        return productAttributeCategoryMapper.select(c -> c.limit(pageSize).offset(pageIndex * pageSize));
    }

    @Override
    public List<ProductAttributeCategoryDto> listWithAttributes() {
        return productAttributeDao.selectCategoryWithAttributes();
    }
}