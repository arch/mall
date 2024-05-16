/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.PromotionKind;
import com.mall.dao.ProductDao;
import com.mall.domain.PromotionCartItem;
import com.mall.domain.PromotionProduct;
import com.mall.model.OmsCartItem;
import com.mall.model.PmsProductFullReduction;
import com.mall.model.PmsProductLadder;
import com.mall.model.PmsSkuStock;
import com.mall.service.PromotionService;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class PromotionServiceImpl implements PromotionService {
    private final ProductDao productDao;

    public PromotionServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<PromotionCartItem> calcCartPromotion(List<OmsCartItem> items) {
        // 1.先根据productId对CartItem进行分组，以spu为单位进行计算优惠
        Map<Long, List<OmsCartItem>> productCartMap = groupCartItemBySpu(items);
        // 2.查询所有商品的优惠相关信息
        List<PromotionProduct> promotionProducts = getPromotionProducts(items);
        // 3.根据商品促销类型计算商品促销优惠价格
        List<PromotionCartItem> promotionCartItems = new ArrayList<>();
        for (Map.Entry<Long, List<OmsCartItem>> entry : productCartMap.entrySet()) {
            Long productId = entry.getKey();
            PromotionProduct promotionProduct = getPromotionProductById(productId, promotionProducts);
            List<OmsCartItem> cartItems = entry.getValue();
            PromotionKind kind = PromotionKind.valueOf(promotionProduct.getPromotionType());
            if (kind == PromotionKind.PromotionPrice) {
                // 单品促销
                for (OmsCartItem item : cartItems) {
                    PromotionCartItem promotionCartItem = new PromotionCartItem();
                    BeanUtils.copyProperties(item, promotionCartItem);
                    promotionCartItem.setPromotionMessage("单品促销");
                    // 商品原价-促销价
                    PmsSkuStock stock = getOriginalPrice(promotionProduct, item.getProductSkuId());
                    BigDecimal originalPrice = stock.getPrice();
                    // 单品促销使用原价
                    promotionCartItem.setPrice(originalPrice);
                    promotionCartItem.setReduceAmount(originalPrice.subtract(stock.getPromotionPrice()));
                    promotionCartItem.setRealStock(stock.getStock() - stock.getLockStock());
                    promotionCartItem.setIntegration(promotionProduct.getGiftPoint());
                    promotionCartItem.setGrowth(promotionProduct.getGiftGrowth());
                    promotionCartItems.add(promotionCartItem);
                }
            } else if (kind == PromotionKind.LadderPrice) {
                // 打折优惠
                int count = getCartItemCount(cartItems);
                PmsProductLadder ladder = getProductLadder(count, promotionProduct.getProductLadderList());
                if (ladder != null) {
                    for (OmsCartItem item : cartItems) {
                        PromotionCartItem promotionCartItem = new PromotionCartItem();
                        BeanUtils.copyProperties(item, promotionCartItem);
                        String message = getLadderPromotionMessage(ladder);
                        promotionCartItem.setPromotionMessage(message);
                        // 商品原价-折扣*商品原价
                        PmsSkuStock stock = getOriginalPrice(promotionProduct, item.getProductSkuId());
                        BigDecimal originalPrice = stock.getPrice();
                        BigDecimal reduceAmount = originalPrice.subtract(ladder.getDiscount().multiply(originalPrice));
                        promotionCartItem.setReduceAmount(reduceAmount);
                        promotionCartItem.setRealStock(stock.getStock() - stock.getLockStock());
                        promotionCartItem.setIntegration(promotionProduct.getGiftPoint());
                        promotionCartItem.setGrowth(promotionProduct.getGiftGrowth());
                        promotionCartItems.add(promotionCartItem);
                    }
                } else {
                    handleNoReduce(promotionCartItems, cartItems, promotionProduct);
                }
            } else if (kind == PromotionKind.FullReductionPrice) {
                // 满减
                BigDecimal totalAmount = getCartItemAmount(cartItems, promotionProducts);
                PmsProductFullReduction fullReduction = getProductFullReduction(totalAmount, promotionProduct.getProductFullReductionList());
                if (fullReduction != null) {
                    for (OmsCartItem item : cartItems) {
                        PromotionCartItem promotionCartItem = new PromotionCartItem();
                        BeanUtils.copyProperties(item, promotionCartItem);
                        String message = getFullReductionPromotionMessage(fullReduction);
                        promotionCartItem.setPromotionMessage(message);
                        // (商品原价/总价)*满减金额
                        PmsSkuStock stock = getOriginalPrice(promotionProduct, item.getProductSkuId());
                        BigDecimal originalPrice = stock.getPrice();
                        BigDecimal reduceAmount = originalPrice.divide(totalAmount, RoundingMode.HALF_EVEN).multiply(fullReduction.getReducePrice());
                        promotionCartItem.setReduceAmount(reduceAmount);
                        promotionCartItem.setRealStock(stock.getStock() - stock.getLockStock());
                        promotionCartItem.setIntegration(promotionProduct.getGiftPoint());
                        promotionCartItem.setGrowth(promotionProduct.getGiftGrowth());
                        promotionCartItems.add(promotionCartItem);
                    }
                } else {
                    handleNoReduce(promotionCartItems, cartItems, promotionProduct);
                }
            } else {
                // 无优惠
                handleNoReduce(promotionCartItems, cartItems, promotionProduct);
            }
        }

        return promotionCartItems;
    }

    /**
     * 查询所有商品的优惠相关信息
     */
    private List<PromotionProduct> getPromotionProducts(List<OmsCartItem> items) {
        List<Long> productIds = items.stream().map(OmsCartItem::getProductId).collect(Collectors.toList());
        if (productIds.isEmpty()) {
            return Collections.emptyList();
        }
        return productDao.getPromotionProductList(productIds);
    }

    /**
     * 以SPU为单位对购物车中商品进行分组
     */
    private Map<Long, List<OmsCartItem>> groupCartItemBySpu(List<OmsCartItem> items) {
        Map<Long, List<OmsCartItem>> productCartMap = new TreeMap<>();
        for (OmsCartItem item : items) {
            List<OmsCartItem> list = productCartMap.get(item.getProductId());
            if (list == null) {
                list = new ArrayList<>();
                list.add(item);
                productCartMap.put(item.getProductId(), list);
            } else {
                list.add(item);
            }
        }
        return productCartMap;
    }

    /**
     * 获取满减促销消息
     */
    private String getFullReductionPromotionMessage(PmsProductFullReduction fullReduction) {
        StringBuilder sb = new StringBuilder();
        sb.append("满减优惠：");
        sb.append("满");
        sb.append(fullReduction.getFullPrice());
        sb.append("元，");
        sb.append("减");
        sb.append(fullReduction.getReducePrice());
        sb.append("元");
        return sb.toString();
    }

    /**
     * 对没满足优惠条件的商品进行处理
     */
    private void handleNoReduce(List<PromotionCartItem> promotionCartItems, List<OmsCartItem> cartItems, PromotionProduct promotionProduct) {
        for (OmsCartItem item : cartItems) {
            PromotionCartItem promotionCartItem = new PromotionCartItem();
            BeanUtils.copyProperties(item, promotionCartItem);
            promotionCartItem.setPromotionMessage("无优惠");
            promotionCartItem.setReduceAmount(new BigDecimal(0));
            PmsSkuStock stock = getOriginalPrice(promotionProduct, item.getProductSkuId());
            promotionCartItem.setRealStock(stock.getStock() - stock.getLockStock());
            promotionCartItem.setIntegration(promotionProduct.getGiftPoint());
            promotionCartItem.setGrowth(promotionProduct.getGiftGrowth());
            promotionCartItems.add(promotionCartItem);
        }
    }

    private PmsProductFullReduction getProductFullReduction(BigDecimal totalAmount, List<PmsProductFullReduction> fullReductions) {
        // 按条件从高到低排序
        fullReductions.sort((o1, o2) -> o2.getFullPrice().subtract(o1.getFullPrice()).intValue());
        for (PmsProductFullReduction fullReduction : fullReductions) {
            if (totalAmount.subtract(fullReduction.getFullPrice()).intValue() >= 0) {
                return fullReduction;
            }
        }
        return null;
    }

    /**
     * 获取打折优惠的促销信息
     */
    private String getLadderPromotionMessage(PmsProductLadder ladder) {
        StringBuilder sb = new StringBuilder();
        sb.append("打折优惠：");
        sb.append("满");
        sb.append(ladder.getCount());
        sb.append("件，");
        sb.append("打");
        sb.append(ladder.getDiscount().multiply(new BigDecimal(10)));
        sb.append("折");
        return sb.toString();
    }

    /**
     * 根据购买商品数量获取满足条件的打折优惠策略
     */
    private PmsProductLadder getProductLadder(int count, List<PmsProductLadder> productLadders) {
        // 按数量从大到小排序
        productLadders.sort((o1, o2) -> o2.getCount() - o1.getCount());
        for (PmsProductLadder productLadder : productLadders) {
            if (count >= productLadder.getCount()) {
                return productLadder;
            }
        }
        return null;
    }

    /**
     * 获取购物车中指定商品的数量
     */
    private int getCartItemCount(List<OmsCartItem> items) {
        int count = 0;
        for (var item : items) {
            count += item.getQuantity();
        }
        return count;
    }

    /**
     * 获取购物车中指定商品的总价
     */
    private BigDecimal getCartItemAmount(List<OmsCartItem> items, List<PromotionProduct> promotionProducts) {
        BigDecimal amount = new BigDecimal(0);
        for (OmsCartItem item : items) {
            // 计算出商品原价
            PromotionProduct promotionProduct = getPromotionProductById(item.getProductId(), promotionProducts);
            PmsSkuStock stock = getOriginalPrice(promotionProduct, item.getProductSkuId());
            amount = amount.add(stock.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return amount;
    }

    /**
     * 获取商品的原价
     */
    private PmsSkuStock getOriginalPrice(PromotionProduct promotionProduct, Long productSkuId) {
        for (PmsSkuStock skuStock : promotionProduct.getSkuStockList()) {
            if (productSkuId.equals(skuStock.getId())) {
                return skuStock;
            }
        }

        throw new IllegalArgumentException("cannot find out the SKU: " + productSkuId);
    }

    /**
     * 根据商品id获取商品的促销信息
     */
    private PromotionProduct getPromotionProductById(Long productId, List<PromotionProduct> promotionProducts) {
        for (PromotionProduct promotionProduct : promotionProducts) {
            if (productId.equals(promotionProduct.getId())) {
                return promotionProduct;
            }
        }

        throw new IllegalArgumentException("cannot find out the product: " + productId);
    }
}