/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.dao.HomeDao;
import com.mall.domain.FlashPromotionProduct;
import com.mall.domain.HomeContent;
import com.mall.domain.HomeFlashPromotion;
import com.mall.mapper.CmsSubjectMapper;
import com.mall.mapper.PmsBrandMapper;
import com.mall.mapper.PmsProductCategoryMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.mapper.SmsFlashPromotionMapper;
import com.mall.mapper.SmsFlashPromotionSessionMapper;
import com.mall.mapper.SmsHomeAdvertiseDynamicSqlSupport;
import com.mall.mapper.SmsHomeAdvertiseMapper;
import com.mall.model.CmsSubject;
import com.mall.model.PmsProduct;
import com.mall.model.PmsProductCategory;
import com.mall.model.SmsFlashPromotion;
import com.mall.model.SmsFlashPromotionSession;
import com.mall.model.SmsHomeAdvertise;
import com.mall.service.HomeService;
import com.mall.model.PmsBrand;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.mall.mapper.CmsSubjectDynamicSqlSupport.cmsSubject;
import static com.mall.mapper.PmsBrandDynamicSqlSupport.*;
import static com.mall.mapper.PmsProductCategoryDynamicSqlSupport.pmsProductCategory;
import static com.mall.mapper.PmsProductDynamicSqlSupport.pmsProduct;
import static com.mall.mapper.SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion;
import static com.mall.mapper.SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession;
import static com.mall.mapper.SmsHomeAdvertiseDynamicSqlSupport.type;
import static com.mall.mapper.SmsHomeBrandDynamicSqlSupport.smsHomeBrand;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class HomeServiceImpl implements HomeService {
    private final SmsHomeAdvertiseMapper advertiseMapper;
    private final PmsBrandMapper brandMapper;
    private final PmsProductMapper productMapper;
    private final CmsSubjectMapper subjectMapper;
    private final PmsProductCategoryMapper productCategoryMapper;
    private final SmsFlashPromotionMapper flashPromotionMapper;
    private final SmsFlashPromotionSessionMapper promotionSessionMapper;
    private final HomeDao homeDao;

    public HomeServiceImpl(SmsHomeAdvertiseMapper advertiseMapper,
                           PmsBrandMapper brandMapper,
                           PmsProductMapper productMapper,
                           CmsSubjectMapper subjectMapper,
                           PmsProductCategoryMapper productCategoryMapper,
                           SmsFlashPromotionMapper flashPromotionMapper,
                           SmsFlashPromotionSessionMapper promotionSessionMapper,
                           HomeDao homeDao) {
        this.advertiseMapper = advertiseMapper;
        this.brandMapper = brandMapper;
        this.productMapper = productMapper;
        this.subjectMapper = subjectMapper;
        this.productCategoryMapper = productCategoryMapper;
        this.flashPromotionMapper = flashPromotionMapper;
        this.promotionSessionMapper = promotionSessionMapper;
        this.homeDao = homeDao;
    }

    @Override
    public HomeContent content() {
        HomeContent content = new HomeContent();
        // 获取首页广告
        content.setAdvertiseList(getHomeAdvertiseList());
        // 获取推荐品牌
        content.setBrandList(getRecommendBrandList(0, 6));
        // 获取秒杀信息
        content.setHomeFlashPromotion(getHomeFlashPromotion());
        // 获取新品推荐
        content.setNewProductList(homeDao.getNewProductList(0, 4));
        // 获取人气推荐
        content.setHotProductList(homeDao.getHotProductList(0, 4));
        // 获取推荐专题
        content.setSubjectList(homeDao.getRecommendSubjectList(0, 4));
        return content;
    }

    @Override
    public List<PmsProduct> recommendProductList(int pageIndex, int pageSize) {
        // TODO: 暂时默认推荐所有商品
        return productMapper.select(c ->
                c.where(pmsProduct.deleteStatus, isEqualTo(0))
                        .and(pmsProduct.publishStatus, isEqualTo(1))
                        .limit(pageSize)
                        .offset(pageIndex * pageSize));
    }

    @Override
    public List<PmsProductCategory> getProductCateList(long parentId) {
        return productCategoryMapper.select(c ->
                c.where(pmsProductCategory.showStatus, isEqualTo(1))
                .and(pmsProductCategory.parentId, isEqualTo(parentId))
                .orderBy(pmsProductCategory.sort.descending()));
    }

    @Override
    public List<CmsSubject> getSubjectList(Long categoryId, int pageIndex, int pageSize) {
        if(categoryId != null){
            return subjectMapper.select(c ->
                    c.where(cmsSubject.showStatus, isEqualTo(1))
                            .and(cmsSubject.categoryId, isEqualTo(categoryId))
                            .limit(pageSize)
                            .offset(pageIndex * pageSize));
        }
        return subjectMapper.select(c ->
                c.where(cmsSubject.showStatus, isEqualTo(1)));
    }

    @Override
    public List<PmsProduct> hotProductList(int pageIndex, int pageSize) {
        return homeDao.getHotProductList(pageSize * pageIndex, pageSize);
    }

    @Override
    public List<PmsProduct> newProductList(int pageIndex, int pageSize) {
        return homeDao.getNewProductList(pageSize * pageIndex, pageSize);
    }

    private List<SmsHomeAdvertise> getHomeAdvertiseList() {
        return advertiseMapper.select(c -> c.where(type, isEqualTo(1)).orderBy(SmsHomeAdvertiseDynamicSqlSupport.sort.descending()));
    }

    private List<PmsBrand> getRecommendBrandList(int pageIndex, int pageSize) {
        SelectStatementProvider selectStatement =
                select(pmsBrand.id, name, firstLetter, pmsBrand.sort, factoryStatus, showStatus, productCount, productCommentCount, logo, bigPic, brandStory)
                        .from(smsHomeBrand)
                        .leftJoin(pmsBrand).on(smsHomeBrand.brandId, equalTo(pmsBrand.id))
                        .where(smsHomeBrand.recommendStatus, isEqualTo(1))
                        .and(pmsBrand.showStatus, isEqualTo(1))
                        .orderBy(smsHomeBrand.sort.descending())
                        .limit(pageSize).offset(pageIndex * pageSize)
                        .build().render(RenderingStrategies.MYBATIS3);
        return brandMapper.selectMany(selectStatement);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        // 获取当前秒杀活动
        SmsFlashPromotion flashPromotion = getFlashPromotion(LocalDate.now());
        if (flashPromotion != null) {
            // 获取当前秒杀场次
            SmsFlashPromotionSession flashPromotionSession = getFlashPromotionSession(LocalTime.now());
            if (flashPromotionSession != null) {
                homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
                homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
                // 获取下一个秒杀场次
                SmsFlashPromotionSession nextSession = getNextFlashPromotionSession(homeFlashPromotion.getStartTime());
                if(nextSession!=null){
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                // 获取秒杀商品
                List<FlashPromotionProduct> flashProductList = homeDao.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }

        return homeFlashPromotion;
    }

    // 根据时间获取秒杀活动
    private SmsFlashPromotion getFlashPromotion(LocalDate date) {
        List<SmsFlashPromotion> flashPromotionList =
                flashPromotionMapper.select(c ->
                        c.where(smsFlashPromotion.status, isEqualTo(1))
                                .and(smsFlashPromotion.startDate, isLessThanOrEqualTo(date))
                                .and(smsFlashPromotion.endDate, isGreaterThanOrEqualTo(date)));
        if (!CollectionUtils.isEmpty(flashPromotionList)) {
            return flashPromotionList.get(0);
        }
        return null;
    }

    // 根据时间获取秒杀场次
    private SmsFlashPromotionSession getFlashPromotionSession(LocalTime time) {
        List<SmsFlashPromotionSession> promotionSessionList =
                promotionSessionMapper.select(c ->
                        c.where(smsFlashPromotionSession.startTime, isLessThanOrEqualTo(time))
                                .and(smsFlashPromotionSession.endTime, isGreaterThanOrEqualTo(time)));
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }

    // 获取下一个场次信息
    private SmsFlashPromotionSession getNextFlashPromotionSession(LocalTime time) {
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.select(c ->
                c.where(smsFlashPromotionSession.startTime, isGreaterThan(time))
                        .orderBy(smsFlashPromotionSession.startTime));
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }
}