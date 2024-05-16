/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import static com.mall.mapper.PmsCommentDynamicSqlSupport.pmsComment;
import static com.mall.mapper.PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue;
import static com.mall.mapper.PmsProductDynamicSqlSupport.pmsProduct;
import static com.mall.mapper.PmsProductFullReductionDynamicSqlSupport.pmsProductFullReduction;
import static com.mall.mapper.PmsProductLadderDynamicSqlSupport.pmsProductLadder;
import static com.mall.mapper.PmsSkuStockDynamicSqlSupport.pmsSkuStock;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.dao.ProductDao;
import com.mall.domain.ProductCategory;
import com.mall.domain.ProductDetail;
import com.mall.service.ProductService;
import com.mall.mapper.PmsBrandMapper;
import com.mall.mapper.PmsCommentMapper;
import com.mall.mapper.PmsProductAttributeMapper;
import com.mall.mapper.PmsProductAttributeValueMapper;
import com.mall.mapper.PmsProductCategoryMapper;
import com.mall.mapper.PmsProductFullReductionMapper;
import com.mall.mapper.PmsProductLadderMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.mapper.PmsSkuStockMapper;
import com.mall.model.PmsBrand;
import com.mall.model.PmsProduct;
import com.mall.model.PmsProductAttribute;
import com.mall.model.PmsProductAttributeValue;
import com.mall.model.PmsProductCategory;
import com.mall.model.PmsProductFullReduction;
import com.mall.model.PmsProductLadder;
import com.mall.model.PmsSkuStock;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {
    private final PmsProductMapper productMapper;
    private final PmsProductCategoryMapper productCategoryMapper;
    private final PmsBrandMapper brandMapper;
    private final PmsProductAttributeMapper productAttributeMapper;
    private final PmsProductAttributeValueMapper productAttributeValueMapper;
    private final PmsSkuStockMapper skuStockMapper;
    private final PmsProductLadderMapper productLadderMapper;
    private final PmsProductFullReductionMapper productFullReductionMapper;
    private final PmsCommentMapper commentMapper;
    private final ProductDao productDao;

    public ProductServiceImpl(PmsProductMapper productMapper,
            PmsProductCategoryMapper productCategoryMapper,
            PmsBrandMapper brandMapper,
            PmsProductAttributeMapper productAttributeMapper,
            PmsProductAttributeValueMapper productAttributeValueMapper,
            PmsSkuStockMapper skuStockMapper,
            PmsProductLadderMapper productLadderMapper,
            PmsProductFullReductionMapper productFullReductionMapper,
            PmsCommentMapper commentMapper, ProductDao productDao) {
        this.productMapper = productMapper;
        this.productCategoryMapper = productCategoryMapper;
        this.brandMapper = brandMapper;
        this.productAttributeMapper = productAttributeMapper;
        this.productAttributeValueMapper = productAttributeValueMapper;
        this.skuStockMapper = skuStockMapper;
        this.productLadderMapper = productLadderMapper;
        this.productFullReductionMapper = productFullReductionMapper;
        this.commentMapper = commentMapper;
        this.productDao = productDao;
    }

    @Override
    public List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, int pageIndex, int pageSize, Integer sort) {
        return productMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = c.where(pmsProduct.deleteStatus, isEqualTo(0));
            if (brandId != null) {
                whereDSL.and(pmsProduct.brandId, isEqualTo(brandId));
            }
            if (productCategoryId != null) {
                whereDSL.and(pmsProduct.categoryId, isEqualTo(productCategoryId));
            }
            if (StringUtils.hasText(keyword)) {
                whereDSL.and(pmsProduct.name, isLike("%" + keyword + "%"));
            }

            if (sort == 1) {
                c.orderBy(pmsProduct.id.descending());
            } else if (sort == 2) {
                c.orderBy(pmsProduct.sale.descending());
            } else if (sort == 3) {
                c.orderBy(pmsProduct.price);
            } else if (sort == 4) {
                c.orderBy(pmsProduct.price.descending());
            }

            c.limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }

    @Override
    public List<ProductCategory> categoryTree() {
        List<PmsProductCategory> all = productCategoryMapper.select(SelectDSLCompleter.allRows());
        return all.stream()
                .filter(item -> item.getParentId().equals(0L))
                .map(item -> covert(item, all)).collect(Collectors.toList());
    }

    private ProductCategory covert(PmsProductCategory item, List<PmsProductCategory> all) {
        ProductCategory node = new ProductCategory();
        BeanUtils.copyProperties(item, node);
        List<ProductCategory> children = all.stream()
                .filter(subItem -> subItem.getParentId().equals(item.getId()))
                .map(subItem -> covert(subItem, all)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public ProductDetail detail(long id) {
        ProductDetail result = new ProductDetail();
        // 获取商品信息
        Optional<PmsProduct> optional = productMapper.selectByPrimaryKey(id);
        if (optional.isEmpty()) {
            return null;
        }
        PmsProduct product = optional.get();
        result.setProduct(product);

        // 获取品牌信息
        Optional<PmsBrand> brand = brandMapper.selectByPrimaryKey(product.getBrandId());
        brand.ifPresent(result::setBrand);

        // 获取商品属性信息
        List<PmsProductAttribute> productAttributeList = productAttributeMapper.select(c ->
                c.where(pmsProduct.attributeCategoryId, isEqualTo(product.getAttributeCategoryId())));
        result.setAttributes(productAttributeList);

        // 获取商品属性值信息
        if (!productAttributeList.isEmpty()) {
            List<Long> attributeIds = productAttributeList.stream().map(PmsProductAttribute::getId).collect(Collectors.toList());
            List<PmsProductAttributeValue> productAttributeValueList = productAttributeValueMapper.select(c ->
                    c.where(pmsProductAttributeValue.productId, isEqualTo(product.getId()))
                            .and(pmsProductAttributeValue.productAttributeId, isIn(attributeIds)));
            result.setAttributeValues(productAttributeValueList);
        }

        // 获取商品SKU库存信息
        List<PmsSkuStock> skuStockList = skuStockMapper.select(c -> c.where(pmsSkuStock.productId, isEqualTo(product.getId())));
        result.setStocks(skuStockList);

        // 商品阶梯价格设置
        if (product.getPromotionType() == 3) {
            List<PmsProductLadder> productLadderList = productLadderMapper.select(c -> c.where(pmsProductLadder.productId, isEqualTo(product.getId())));
            result.setLadders(productLadderList);
        }
        // 商品满减价格设置
        if (product.getPromotionType() == 4) {
            List<PmsProductFullReduction> productFullReductionList = productFullReductionMapper.select(c -> c.where(pmsProductFullReduction.productId, isEqualTo(product.getId())));
            result.setFullReductions(productFullReductionList);
        }

        // 商品可用优惠券
        result.setCoupons(productDao.getAvailableCouponList(product.getId(), product.getCategoryId()));

        // 商品评论
        long commentCount = commentMapper.count(c -> c.where(pmsComment.productId, isEqualTo(product.getId())));
        result.setCommentCount(commentCount);

        return result;
    }
}