/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service.impl;

import static com.mall.mapper.CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation;
import static com.mall.mapper.CmsSubjectProductRelationDynamicSqlSupport.cmsSubjectProductRelation;
import static com.mall.mapper.PmsMemberPriceDynamicSqlSupport.pmsMemberPrice;
import static com.mall.mapper.PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue;
import static com.mall.mapper.PmsProductDynamicSqlSupport.pmsProduct;
import static com.mall.mapper.PmsProductFullReductionDynamicSqlSupport.pmsProductFullReduction;
import static com.mall.mapper.PmsProductLadderDynamicSqlSupport.pmsProductLadder;
import static com.mall.mapper.PmsSkuStockDynamicSqlSupport.pmsSkuStock;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.pms.dao.ProductDao;
import com.mall.pms.dao.ProductPriceDao;
import com.mall.pms.dao.SkuStockDao;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.CmsPreferenceAreaProductRelationMapper;
import com.mall.mapper.CmsSubjectProductRelationMapper;
import com.mall.mapper.PmsMemberPriceMapper;
import com.mall.mapper.PmsProductAttributeValueMapper;
import com.mall.mapper.PmsProductFullReductionMapper;
import com.mall.mapper.PmsProductLadderMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.mapper.PmsSkuStockMapper;
import com.mall.model.CmsSubjectProductRelation;
import com.mall.model.PmsMemberPrice;
import com.mall.model.PmsProduct;
import com.mall.model.PmsProductAttributeValue;
import com.mall.model.PmsProductFullReduction;
import com.mall.model.PmsProductLadder;
import com.mall.model.PmsProductVerifyRecord;
import com.mall.model.PmsSkuStock;
import com.mall.pms.dto.ProductDto;
import com.mall.pms.dto.ProductEditDto;
import com.mall.pms.dto.ProductQuery;
import com.mall.pms.service.ProductService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {
    private final PmsProductMapper productMapper;

    private final PmsMemberPriceMapper memberPriceMapper;

    private final PmsProductLadderMapper productLadderMapper;

    private final PmsProductFullReductionMapper productFullReductionMapper;

    private final PmsSkuStockMapper skuStockMapper;

    private final PmsProductAttributeValueMapper productAttributeValueMapper;

    private final CmsSubjectProductRelationMapper subjectProductRelationMapper;

    private final CmsPreferenceAreaProductRelationMapper preferenceAreaProductRelationMapper;

    private final ProductPriceDao productPriceDao;

    private final SkuStockDao skuStockDao;

    private final ProductDao productDao;

    public ProductServiceImpl(PmsProductMapper productMapper,
                              PmsMemberPriceMapper memberPriceMapper,
                              PmsProductLadderMapper productLadderMapper,
                              PmsProductFullReductionMapper productFullReductionMapper,
                              PmsSkuStockMapper skuStockMapper,
                              PmsProductAttributeValueMapper productAttributeValueMapper,
                              CmsSubjectProductRelationMapper subjectProductRelationMapper,
                              CmsPreferenceAreaProductRelationMapper preferenceAreaProductRelationMapper,
                              ProductPriceDao productPriceDao, SkuStockDao skuStockDao, ProductDao productDao) {
        this.productMapper = productMapper;
        this.memberPriceMapper = memberPriceMapper;
        this.productLadderMapper = productLadderMapper;
        this.productFullReductionMapper = productFullReductionMapper;
        this.skuStockMapper = skuStockMapper;
        this.productAttributeValueMapper = productAttributeValueMapper;
        this.subjectProductRelationMapper = subjectProductRelationMapper;
        this.preferenceAreaProductRelationMapper = preferenceAreaProductRelationMapper;
        this.productPriceDao = productPriceDao;
        this.skuStockDao = skuStockDao;
        this.productDao = productDao;
    }

    @Override
    public void create(ProductDto product) {
        //创建商品
        product.setId(null);
        int count = productMapper.insertSelective(product);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();

        //会员价格
        insertMemberPrice(product, productId);

        //阶梯价格
        insertLadderPrices(product, productId);

        //满减价格
        insertFullReductionPrices(product, productId);

        //处理sku的编码
        generateSkuStockCode(product.getSkuStocks(), productId);

        //添加sku库存信息
        insertSkuStocks(product.getSkuStocks(), productId);

        //添加商品参数,添加自定义商品规格
        insertAttributeValues(product, productId);

        //关联专题
        insertSubjectRelation(product, productId);

        //关联优选
        insertPreferenceAreaRelation(product, productId);
    }

    @Override
    public List<PmsProduct> list(ProductQuery productQuery, int pageIndex, int pageSize) {
        return productMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = c.where(pmsProduct.deleteStatus, isEqualTo(0));
            if (productQuery.getPublishStatus() != null) {
                whereDSL.and(pmsProduct.publishStatus, isEqualTo(productQuery.getPublishStatus()));
            }
            if (productQuery.getVerifyStatus() != null) {
                whereDSL.and(pmsProduct.verifyStatus, isEqualTo(productQuery.getVerifyStatus()));
            }
            if (StringUtils.hasText(productQuery.getKeyword())) {
                whereDSL.and(pmsProduct.name, isLike("%" + productQuery.getKeyword() + "%"));
            }
            if (StringUtils.hasText(productQuery.getProductSn())) {
                whereDSL.and(pmsProduct.productSn, isEqualTo(productQuery.getProductSn()));
            }
            if (productQuery.getBrandId() != null) {
                whereDSL.and(pmsProduct.brandId, isEqualTo(productQuery.getBrandId()));
            }
            if (productQuery.getProductCategoryId() != null) {
                whereDSL.and(pmsProduct.categoryId, isEqualTo(productQuery.getProductCategoryId()));
            }

            c.limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }

    @Override
    public ProductEditDto getEdit(long id) {
        return productDao.getEdit(id);
    }

    @Override
    public void update(long id, ProductDto product) {
        product.setId(id);
        int count = productMapper.updateByPrimaryKeySelective(product);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        //会员价格
        memberPriceMapper.delete(c -> c.where(pmsMemberPrice.productId, isEqualTo(id)));
        insertMemberPrice(product, id);

        //阶梯价格
        productLadderMapper.delete(c -> c.where(pmsProductLadder.productId, isEqualTo(id)));
        insertLadderPrices(product, id);

        //满减价格
        productFullReductionMapper.delete(c -> c.where(pmsProductFullReduction.productId, isEqualTo(id)));
        insertFullReductionPrices(product, id);

        //修改sku库存信息
        updateSkuStocks(id, product);

        //修改商品参数,添加自定义商品规格
        productAttributeValueMapper.delete(c -> c.where(pmsProductAttributeValue.productId, isEqualTo(id)));
        insertAttributeValues(product, id);

        //关联专题
        subjectProductRelationMapper.delete(c -> c.where(cmsSubjectProductRelation.productId, isEqualTo(id)));
        insertSubjectRelation(product, id);

        //关联优选
        preferenceAreaProductRelationMapper.delete(c -> c.where(cmsPreferenceAreaProductRelation.productId, isEqualTo(id)));
        insertPreferenceAreaRelation(product, id);
    }

    private void updateSkuStocks(long id, ProductDto product) {
        //当前的sku信息
        List<PmsSkuStock> currSkuList = product.getSkuStocks();

        //当前没有sku直接删除
        if (CollectionUtils.isEmpty(currSkuList)) {
            skuStockMapper.delete(c -> c.where(pmsSkuStock.id, isEqualTo(id)));
            return;
        }

        //获取初始sku信息
        List<PmsSkuStock> oriStuList = skuStockMapper.select(c -> c.where(pmsSkuStock.id, isEqualTo(id)));

        //获取新增sku信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item -> item.getId() == null).collect(Collectors.toList());

        //获取需要更新的sku信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item -> item.getId() != null).collect(Collectors.toList());

        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());

        //获取需要删除的sku信息
        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item -> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());

        generateSkuStockCode(insertSkuList, id);
        generateSkuStockCode(updateSkuList, id);

        //新增sku
        if (!CollectionUtils.isEmpty(insertSkuList)) {
            insertSkuStocks(insertSkuList, id);
        }

        //删除sku
        if (!CollectionUtils.isEmpty(removeSkuList)) {
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            skuStockMapper.delete(c -> c.where(pmsSkuStock.id, isIn(removeSkuIds)));
        }

        //修改sku
        if (!CollectionUtils.isEmpty(updateSkuList)) {
            for (PmsSkuStock pmsSkuStock : updateSkuList) {
                skuStockMapper.updateByPrimaryKeySelective(pmsSkuStock);
            }
        }
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        return productMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = c.where(pmsProduct.deleteStatus, isEqualTo(0));
            if (StringUtils.hasText(keyword)) {
                whereDSL.and(pmsProduct.name, isLike("%" + keyword + "%"))
                        .or(pmsProduct.deleteStatus, isEqualTo(0))
                        .and(pmsProduct.productSn, isLike("%" + keyword + "%"));
            }
            return c;
        });
    }

    @Override
    public void updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        if (ids.isEmpty())
            return;
        int count = productMapper.update(c ->
                c.set(pmsProduct.verifyStatus).equalTo(verifyStatus)
                        .where(pmsProduct.id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);

        //修改完审核状态后插入审核记录
        List<PmsProductVerifyRecord> list = new ArrayList<>();
        for (Long id : ids) {
            PmsProductVerifyRecord record = new PmsProductVerifyRecord();
            record.setProductId(id);
            record.setCreateTime(LocalDateTime.now());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVerifyMan("test");
            list.add(record);
        }

        count = productDao.insertVerifyRecord(list);
        Assert.ensure(count == list.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void updatePublishStatus(List<Long> ids, Integer publishStatus) {
        if (ids.isEmpty())
            return;
        int count = productMapper.update(c ->
                c.set(pmsProduct.publishStatus).equalTo(publishStatus)
                        .where(pmsProduct.id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        if (ids.isEmpty())
            return;
        int count = productMapper.update(c ->
                c.set(pmsProduct.recommendStatus).equalTo(recommendStatus)
                        .where(pmsProduct.id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateNewStatus(List<Long> ids, Integer newStatus) {
        if (ids.isEmpty())
            return;
        int count = productMapper.update(c ->
                c.set(pmsProduct.newStatus).equalTo(newStatus)
                        .where(pmsProduct.id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        if (ids.isEmpty())
            return;
        int count = productMapper.update(c ->
                c.set(pmsProduct.deleteStatus).equalTo(deleteStatus)
                        .where(pmsProduct.id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    private void generateSkuStockCode(List<PmsSkuStock> skuStocks, Long productId) {
        if (CollectionUtils.isEmpty(skuStocks))
            return;
        for (int i = 0; i < skuStocks.size(); i++) {
            PmsSkuStock skuStock = skuStocks.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                //日期
                String sb = formatter.format(LocalDate.now()) +
                        //四位商品id
                        String.format("%04d", productId) +
                        //三位索引id
                        String.format("%03d", i + 1);
                skuStock.setSkuCode(sb);
            }
        }
    }

    private void insertSkuStocks(List<PmsSkuStock> items, Long productId) {
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        for (PmsSkuStock skuStock : items) {
            skuStock.setId(null);
            skuStock.setProductId(productId);
        }
        int count = skuStockDao.insertList(items);
        Assert.ensure(count == items.size(), StandardCode.SQL_FAILURE);
    }

    private void insertPreferenceAreaRelation(ProductDto product, Long productId) {
        if (CollectionUtils.isEmpty(product.getPreferenceAreaProductRelations())) {
            return;
        }
        for (var relation : product.getPreferenceAreaProductRelations()) {
            relation.setId(null);
            relation.setProductId(productId);
        }
        int count = productDao.insertPreferenceAreaRelation(product.getPreferenceAreaProductRelations());
        Assert.ensure(count == product.getPreferenceAreaProductRelations().size(), StandardCode.SQL_FAILURE);
    }

    private void insertSubjectRelation(ProductDto product, Long productId) {
        if (CollectionUtils.isEmpty(product.getSubjectProductRelations())) {
            return;
        }
        for (CmsSubjectProductRelation relation : product.getSubjectProductRelations()) {
            relation.setId(null);
            relation.setProductId(productId);
        }
        int count = productDao.insertSubjectRelation(product.getSubjectProductRelations());
        Assert.ensure(count == product.getSubjectProductRelations().size(), StandardCode.SQL_FAILURE);
    }

    private void insertAttributeValues(ProductDto product, Long productId) {
        if (CollectionUtils.isEmpty(product.getProductAttributeValues())) {
            return;
        }
        for (PmsProductAttributeValue value : product.getProductAttributeValues()) {
            value.setId(null);
            value.setProductId(productId);
        }
        int count = productDao.insertAttributeValues(product.getProductAttributeValues());
        Assert.ensure(count == product.getProductAttributeValues().size(), StandardCode.SQL_FAILURE);
    }

    private void insertFullReductionPrices(ProductDto product, Long productId) {
        if (CollectionUtils.isEmpty(product.getProductFullReductions())) {
            return;
        }
        for (PmsProductFullReduction productFullReduction : product.getProductFullReductions()) {
            productFullReduction.setId(null);
            productFullReduction.setProductId(productId);
        }
        int count = productPriceDao.insertFullReductionPrices(product.getProductFullReductions());
        Assert.ensure(count == product.getProductFullReductions().size(), StandardCode.SQL_FAILURE);
    }

    private void insertLadderPrices(ProductDto product, Long productId) {
        if (CollectionUtils.isEmpty(product.getProductLadders())) {
            return;
        }
        for (PmsProductLadder productLadder : product.getProductLadders()) {
            productLadder.setId(null);
            productLadder.setProductId(productId);
        }
        int count = productPriceDao.insertLadderPrices(product.getProductLadders());
        Assert.ensure(count == product.getProductLadders().size(), StandardCode.SQL_FAILURE);
    }

    private void insertMemberPrice(ProductDto product, long productId) {
        if (CollectionUtils.isEmpty(product.getMemberPrices())) {
            return;
        }
        for (PmsMemberPrice memberPrice : product.getMemberPrices()) {
            memberPrice.setId(null);
            memberPrice.setProductId(productId);
        }
        int count = productPriceDao.insertMemberPrices(product.getMemberPrices());
        Assert.ensure(count == product.getMemberPrices().size(), StandardCode.SQL_FAILURE);
    }
}