/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service.impl;

import static com.mall.mapper.PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation;
import static com.mall.mapper.PmsProductCategoryDynamicSqlSupport.navStatus;
import static com.mall.mapper.PmsProductCategoryDynamicSqlSupport.pmsProductCategory;
import static com.mall.mapper.PmsProductCategoryDynamicSqlSupport.showStatus;
import static com.mall.mapper.PmsProductDynamicSqlSupport.categoryId;
import static com.mall.mapper.PmsProductDynamicSqlSupport.productCategoryName;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import com.mall.pms.dao.ProductCategoryAttributeRelationDao;
import com.mall.pms.dao.ProductDao;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.PmsProductCategoryAttributeRelationMapper;
import com.mall.mapper.PmsProductCategoryMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.model.PmsProductCategory;
import com.mall.model.PmsProductCategoryAttributeRelation;
import com.mall.pms.dto.ProductCategoryDto;
import com.mall.pms.dto.ProductCategoryTreeDto;
import com.mall.pms.service.ProductCategoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final PmsProductCategoryMapper productCategoryMapper;
    private final PmsProductMapper productMapper;
    private final PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;
    private final ProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;
    private final ProductDao productDao;

    public ProductCategoryServiceImpl(PmsProductCategoryMapper productCategoryMapper,
                                      PmsProductMapper productMapper,
                                      PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper,
                                      ProductCategoryAttributeRelationDao productCategoryAttributeRelationDao,
                                      ProductDao productDao) {
        this.productCategoryMapper = productCategoryMapper;
        this.productMapper = productMapper;
        this.productCategoryAttributeRelationMapper = productCategoryAttributeRelationMapper;
        this.productCategoryAttributeRelationDao = productCategoryAttributeRelationDao;
        this.productDao = productDao;
    }

    @Override
    public void create(ProductCategoryDto productCategoryDto) {
        PmsProductCategory category = new PmsProductCategory().withProductCount(0);
        BeanUtils.copyProperties(productCategoryDto, category);
        // 没有父分类时为一级分类
        setCategoryLevel(category);
        int count = productCategoryMapper.insertSelective(category);
        if (count == 1) {
            // 创建筛选属性关联
            List<Long> productAttributeIdList = productCategoryDto.getProductAttributeIdList();
            if (!CollectionUtils.isEmpty(productAttributeIdList)) {
                insertRelationList(category.getId(), productAttributeIdList);
            }
        }

        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(long id, ProductCategoryDto dto) {
        PmsProductCategory category = new PmsProductCategory().withId(id);
        BeanUtils.copyProperties(dto, category);
        setCategoryLevel(category);

        // 更新商品分类时要更新商品中的名称
        productMapper.update(c -> c.set(productCategoryName).equalTo(dto.getName()).where(categoryId, isEqualTo(id)));

        // 同时更新筛选属性的信息
        productCategoryAttributeRelationMapper.delete(c -> c.where(pmsProductCategoryAttributeRelation.categoryId, isEqualTo(id)));
        if (!dto.getProductAttributeIdList().isEmpty()) {
            insertRelationList(id, dto.getProductAttributeIdList());
        }

        int count = productCategoryMapper.updateByPrimaryKey(category);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public List<PmsProductCategory> getList(long parentId, int pageIndex, int pageSize) {
        return productCategoryMapper.select(c -> c.where(pmsProductCategory.parentId, isEqualTo(parentId)).limit(pageSize).offset(pageIndex * pageSize));
    }

    @Override
    public PmsProductCategory getOne(long id) {
        return productCategoryMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        int count = productCategoryMapper.deleteByPrimaryKey(id);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateNavStatus(List<Long> ids, Integer newStatus) {
        if (ids.isEmpty()) {
            return;
        }
        productCategoryMapper.update(c -> c.set(navStatus).equalTo(newStatus).where(pmsProductCategory.id, isIn(ids)));
    }

    @Override
    public void updateShowStatus(List<Long> ids, Integer newStatus) {
        if (ids.isEmpty()) {
            return;
        }
        productCategoryMapper.update(c -> c.set(showStatus).equalTo(newStatus).where(pmsProductCategory.id, isIn(ids)));
    }

    @Override
    public List<ProductCategoryTreeDto> listWithChildren() {
        return productDao.selectCategoryTree();
    }

    private void setCategoryLevel(PmsProductCategory category) {
        // 没有父分类时为一级分类
        if (category.getParentId() == 0) {
            category.setLevel(0);
        } else {
            // 有父分类时选择根据父分类level设置
            Optional<PmsProductCategory> optional = productCategoryMapper.selectByPrimaryKey(category.getParentId());
            if (optional.isPresent()) {
                category.setLevel(optional.get().getLevel() + 1);
            } else {
                category.setLevel(0);
            }
        }
    }

    private void insertRelationList(Long categoryId, List<Long> productAttributeIds) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIds) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setAttributeId(productAttrId);
            relation.setCategoryId(categoryId);
            relationList.add(relation);
        }

        int count = productCategoryAttributeRelationDao.insertList(relationList);
        Assert.ensure(count == relationList.size(), StandardCode.SQL_FAILURE);
    }
}