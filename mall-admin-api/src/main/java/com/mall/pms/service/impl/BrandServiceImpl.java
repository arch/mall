/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service.impl;

import static com.mall.mapper.PmsBrandDynamicSqlSupport.factoryStatus;
import static com.mall.mapper.PmsBrandDynamicSqlSupport.pmsBrand;
import static com.mall.mapper.PmsProductDynamicSqlSupport.brandId;
import static com.mall.mapper.PmsProductDynamicSqlSupport.pmsProduct;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.PmsBrandMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.model.PmsBrand;
import com.mall.pms.dto.BrandDto;
import com.mall.pms.service.BrandService;
import java.util.List;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
public class BrandServiceImpl implements BrandService {
    private final PmsBrandMapper brandMapper;
    private final PmsProductMapper productMapper;

    public BrandServiceImpl(PmsBrandMapper brandMapper, PmsProductMapper productMapper) {
        this.brandMapper = brandMapper;
        this.productMapper = productMapper;
    }

    @Override
    public List<PmsBrand> listAll() {
        return brandMapper.select(SelectDSLCompleter.allRows());
    }

    @Override
    public List<PmsBrand> pagedList(String keyword, int pageIndex, int pageSize) {
        if (StringUtils.hasText(keyword)) {
            return brandMapper.select(c -> c.where(pmsBrand.name, isLike("%" + keyword + "%")).orderBy(pmsBrand.sort.descending()).limit(pageSize).offset(pageIndex * pageSize));
        }

        return brandMapper.select(c -> c.orderBy(pmsBrand.sort.descending()).limit(pageSize).offset(pageIndex * pageSize));
    }

    @Override
    public void create(BrandDto brandDto) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(brandDto, brand);
        // 如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(brand.getFirstLetter())) {
            brand.setFirstLetter(brand.getName().substring(0, 1));
        }
        int count = brandMapper.insertSelective(brand);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(long id, BrandDto brandDto) {
        PmsBrand brand = new PmsBrand().withId(id);
        BeanUtils.copyProperties(brandDto, brand);
        // 如果创建时首字母为空，取名称的第一个为首字母
        if (ObjectUtils.isEmpty(brand.getFirstLetter())) {
            brand.setFirstLetter(brand.getName().substring(0, 1));
        }

        // 更新品牌时要更新商品中的品牌名称, 但是也许没有，所以更新影响行数可能为0
        productMapper.update(c -> c.set(pmsProduct.brandName).equalTo(brand.getName()).where(brandId, isEqualTo(id)));

        int count = brandMapper.updateByPrimaryKeySelective(brand);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(long id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsBrand getOne(long id) {
        return brandMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public void delete(List<Long> ids) {
        brandMapper.delete(c -> c.where(pmsBrand.id, isIn(ids)));
    }

    @Override
    public void updateShowStatus(List<Long> ids, int newStatus) {
        if (ids.isEmpty()) {
            return;
        }
        brandMapper.update(c -> c.set(pmsBrand.showStatus).equalTo(newStatus).where(pmsBrand.id, isIn(ids)));
    }

    @Override
    public void updateFactoryStatus(List<Long> ids, int newStatus) {
        if (ids.isEmpty()) {
            return;
        }
        brandMapper.update(c -> c.set(factoryStatus).equalTo(newStatus).where(pmsBrand.id, isIn(ids)));
    }
}