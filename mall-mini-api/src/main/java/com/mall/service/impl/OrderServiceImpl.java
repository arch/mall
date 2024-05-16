/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import static com.mall.mapper.OmsOrderDynamicSqlSupport.omsOrder;
import static com.mall.mapper.OmsOrderItemDynamicSqlSupport.omsOrderItem;
import static com.mall.mapper.SmsCouponHistoryDynamicSqlSupport.smsCouponHistory;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import com.mall.crosscut.exception.ApiException;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.service.AddressService;
import com.mall.service.CartItemService;
import com.mall.service.CouponService;
import com.mall.service.MemberService;
import com.mall.service.OrderService;
import com.mall.CouponUsage;
import com.mall.OrderKind;
import com.mall.OrderSources;
import com.mall.OrderStatus;
import com.mall.PayKind;
import com.mall.dao.OrderDao;
import com.mall.dao.OrderItemDao;
import com.mall.domain.CartOrder;
import com.mall.domain.ConfirmOrder;
import com.mall.domain.CouponHistoryDetail;
import com.mall.domain.OrderDetail;
import com.mall.domain.OrderItem;
import com.mall.domain.OrderParam;
import com.mall.domain.PromotionCartItem;
import com.mall.mapper.OmsOrderItemMapper;
import com.mall.mapper.OmsOrderMapper;
import com.mall.mapper.OmsOrderSettingMapper;
import com.mall.mapper.PmsSkuStockMapper;
import com.mall.mapper.SmsCouponHistoryMapper;
import com.mall.mapper.UmsIntegrationConsumeSettingMapper;
import com.mall.model.OmsOrder;
import com.mall.model.OmsOrderItem;
import com.mall.model.OmsOrderSetting;
import com.mall.model.SmsCoupon;
import com.mall.model.UmsIntegrationConsumeSetting;
import com.mall.model.UmsMember;
import com.mall.model.UmsMemberReceiveAddress;
import com.mall.mq.CancelOrderSender;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.util.Pool;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MemberService memberService;
    private final CartItemService cartItemService;
    private final AddressService addressService;
    private final CouponService couponService;
    private final UmsIntegrationConsumeSettingMapper integrationConsumeSettingMapper;
    private final PmsSkuStockMapper skuStockMapper;
    private final OmsOrderMapper orderMapper;
    private final OrderItemDao orderItemDao;
    private final SmsCouponHistoryMapper couponHistoryMapper;
    private final Pool<Jedis> jedisPool;
    private final OrderDao orderDao;
    private final OmsOrderSettingMapper orderSettingMapper;
    private final OmsOrderItemMapper orderItemMapper;
    private final CancelOrderSender cancelOrderSender;

    public OrderServiceImpl(OmsOrderMapper orderMapper,
            MemberService memberService,
            CartItemService cartItemService,
            OmsOrderSettingMapper orderSettingMapper,
            AddressService addressService,
            CouponService couponService,
            UmsIntegrationConsumeSettingMapper integrationConsumeSettingMapper,
            OmsOrderItemMapper orderItemMapper,
            CancelOrderSender cancelOrderSender,
            PmsSkuStockMapper skuStockMapper,
            OrderItemDao orderItemDao,
            SmsCouponHistoryMapper couponHistoryMapper,
            OrderDao orderDao,
            Pool<Jedis> jedisPool) {
        this.orderMapper = orderMapper;
        this.memberService = memberService;
        this.cartItemService = cartItemService;
        this.orderSettingMapper = orderSettingMapper;
        this.addressService = addressService;
        this.couponService = couponService;
        this.integrationConsumeSettingMapper = integrationConsumeSettingMapper;
        this.orderItemMapper = orderItemMapper;
        this.cancelOrderSender = cancelOrderSender;
        this.skuStockMapper = skuStockMapper;
        this.orderItemDao = orderItemDao;
        this.couponHistoryMapper = couponHistoryMapper;
        this.orderDao = orderDao;
        this.jedisPool = jedisPool;
    }

    @Override
    public ConfirmOrder generateConfirmOrder(List<Long> cartIds) {
        Assert.ensure(cartIds.size() > 0, "购物车中没有任何商品");

        ConfirmOrder confirmOrder = new ConfirmOrder();

        // 获取购物车信息
        var currentMember = memberService.getCurrentMember();
        List<PromotionCartItem> items = cartItemService.listPromotion(cartIds);
        confirmOrder.setPromotionCartItems(items);

        // 获取用户收货地址列表
        confirmOrder.setReceiveAddresses(addressService.list());

        // 获取用户可用优惠券列表
        confirmOrder.setCouponHistoryDetails(couponService.listCart(items, 1));

        // 获取用户积分
        confirmOrder.setMemberIntegration(currentMember.getIntegration());

        // 获取积分使用规则
        integrationConsumeSettingMapper.selectByPrimaryKey(1L).ifPresent(confirmOrder::setIntegrationConsumeSetting);

        // 计算总金额、活动优惠、应付金额
        ConfirmOrder.CalcAmount calcAmount = calcCartAmount(items);
        confirmOrder.setCalcAmount(calcAmount);

        return confirmOrder;
    }

    @Override
    public CartOrder generateOrder(OrderParam orderParam) {
        Assert.ensure(orderParam.getMemberReceiveAddressId() != null, "无法生成订单，收货地址不能为空");

        // 获取购物车及优惠信息
        List<PromotionCartItem> promotionCartItems = cartItemService.listPromotion(orderParam.getCartIds());

        Assert.ensure(promotionCartItems.size() > 0, "无法生成订单，获取购物车及优惠信息[为空]");

        // 判断购物车中商品是否都有库存
        ensureHasEnoughStock(promotionCartItems);

        List<OmsOrderItem> items = new ArrayList<>();
        for (var item : promotionCartItems) {
            // 生成下单商品信息
            OmsOrderItem orderItem = new OmsOrderItem();
            orderItem.setProductId(item.getProductId());
            orderItem.setProductName(item.getProductName());
            orderItem.setProductPic(item.getProductPic());
            orderItem.setProductAttr(item.getProductAttr());
            orderItem.setProductBrand(item.getProductBrand());
            orderItem.setProductSn(item.getProductSn());
            orderItem.setProductPrice(item.getPrice());
            orderItem.setProductQuantity(item.getQuantity());
            orderItem.setProductSkuId(item.getProductSkuId());
            orderItem.setProductSkuCode(item.getProductSkuCode());
            orderItem.setProductCategoryId(item.getProductCategoryId());
            orderItem.setPromotionAmount(item.getReduceAmount());
            orderItem.setPromotionName(item.getPromotionMessage());
            orderItem.setGiftIntegration(item.getIntegration());
            orderItem.setGiftGrowth(item.getGrowth());
            items.add(orderItem);
        }

        UmsMember currentMember = memberService.getCurrentMember();

        // 判断使用使用了优惠券
        if (orderParam.getCouponId() == null) {
            // 不用优惠券
            for (var item : items) {
                item.setCouponAmount(new BigDecimal(0));
            }
        } else {
            // 使用优惠券
            CouponHistoryDetail couponHistoryDetail = getUseCoupon(promotionCartItems, orderParam.getCouponId());
            Assert.ensure(couponHistoryDetail != null,
                    StandardCode.BAD_REQUEST.withMessage(String.format("不存在优惠券[%d]", orderParam.getCouponId())));

            // 对下单商品的优惠券进行处理
            handleCouponAmount(items, couponHistoryDetail);
        }

        // 判断是否使用积分
        if (orderParam.getUseIntegration() == null || orderParam.getUseIntegration().equals(0)) {
            // 不使用积分
            for (var item : items) {
                item.setIntegrationAmount(new BigDecimal(0));
            }
        } else {
            // 使用积分
            BigDecimal totalAmount = calcTotalAmount(items);
            BigDecimal integrationAmount = getUseIntegrationAmount(orderParam.getUseIntegration(), totalAmount,
                    currentMember, orderParam.getCouponId() != null);

            Assert.ensure(integrationAmount.compareTo(new BigDecimal(0)) > 0,
                    StandardCode.BAD_REQUEST.withMessage(String.format("积分[%d]不可用", orderParam.getUseIntegration())));

            // 可用情况下分摊到可用商品中
            for (var item : items) {
                BigDecimal perAmount = item.getProductPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN)
                        .multiply(integrationAmount);
                item.setIntegrationAmount(perAmount);
            }
        }

        // 计算实付金额
        handleRealAmount(items);

        // 进行库存锁定
        lockStock(promotionCartItems);

        // 转化为订单信息并插入数据库
        OmsOrder order = new OmsOrder()
                .withMemberId(currentMember.getId())
                .withMemberUsername(currentMember.getUsername())
                .withCreateTime(LocalDateTime.now())
                .withModifyTime(LocalDateTime.now());

        // 根据商品合计、运费、活动优惠、优惠券、积分计算应付金额
        order.setDiscountAmount(new BigDecimal(0));
        order.setTotalAmount(calcTotalAmount(items));
        order.setFreightAmount(new BigDecimal(0));
        order.setPromotionAmount(calcPromotionAmount(items));
        order.setPromotionInfo(getPromotionInfo(items));
        if (orderParam.getCouponId() == null) {
            order.setCouponAmount(new BigDecimal(0));
        } else {
            order.setCouponId(orderParam.getCouponId());
            order.setCouponAmount(calcCouponAmount(items));
        }
        if (orderParam.getUseIntegration() == null) {
            order.setIntegration(0);
            order.setIntegrationAmount(new BigDecimal(0));
        } else {
            order.setIntegration(orderParam.getUseIntegration());
            order.setIntegrationAmount(calcIntegrationAmount(items));
        }
        order.setPayAmount(calcPayAmount(order));

        //支付方式：0->未支付；1->支付宝；2->微信
        order.setPayType(orderParam.getPayType());

        //订单来源：0->PC订单；1->app订单
        order.setSourceType(OrderSources.APP.getSource());

        //订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
        order.setStatus(OrderStatus.UN_PAY.getStatus());

        //订单类型：0->正常订单；1->秒杀订单
        order.setOrderType(OrderKind.NORMAL.getCode());

        //收货人信息：姓名、电话、邮编、地址
        UmsMemberReceiveAddress address = addressService.get(orderParam.getMemberReceiveAddressId());
        order.setReceiverName(address.getName());
        order.setReceiverPhone(address.getPhoneNumber());
        order.setReceiverPostCode(address.getPostCode());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverCity(address.getCity());
        order.setReceiverRegion(address.getRegion());
        order.setReceiverDetailAddress(address.getDetailAddress());

        //0->未确认；1->已确认
        order.setConfirmStatus(0);
        order.setDeleteStatus(0);

        //计算赠送积分
        order.setIntegration(calcGifIntegration(items));

        //计算赠送成长值
        order.setGrowth(calcGiftGrowth(items));

        //生成订单号
        order.setOrderSn(generateOrderSn(order));

        // 设置自动收货天数
        List<OmsOrderSetting> orderSettings = orderSettingMapper.select(SelectDSLCompleter.allRows());
        if (!CollectionUtils.isEmpty(orderSettings)) {
            order.setAutoConfirmDay(orderSettings.get(0).getConfirmOvertime());
        }

        // 写入数据库
        int count = orderMapper.insert(order);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        for (var item : items) {
            item.setOrderId(order.getId());
            item.setOrderSn(order.getOrderSn());
        }
        orderItemDao.insertList(items);

        // 如使用优惠券更新优惠券使用状态
        if (orderParam.getCouponId() != null) {
            updateCouponStatus(orderParam.getCouponId(), currentMember.getId(), 1);
        }

        // 如使用积分需要扣除积分
        if (orderParam.getUseIntegration() != null) {
            order.setUseIntegration(orderParam.getUseIntegration());
            memberService.updateIntegration(currentMember.getId(),
                    currentMember.getIntegration() - orderParam.getUseIntegration());
        }

        // 删除购物车中的下单商品
        deleteCartItemList(promotionCartItems, currentMember);

        // 发送延迟消息取消订单
        cancelAsync(order.getId());

        CartOrder cartOrder = new CartOrder();
        cartOrder.setOrder(order);
        cartOrder.setOrderItems(items);
        return cartOrder;
    }

    @Override
    public boolean paySuccess(long orderId, PayKind kind, String transactionId) {
        // 修改订单支付状态
        OmsOrder order = new OmsOrder();
        order.setId(orderId);
        order.setStatus(OrderStatus.UN_DELIVER.getStatus());
        order.setPaymentTime(LocalDateTime.now());
        order.setModifyTime(LocalDateTime.now());
        order.setPayType(kind.getCode());
        order.setTransactionId(transactionId);
        int count = orderMapper.updateByPrimaryKeySelective(order);

        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        // 恢复所有下单商品的锁定库存，扣减真实库存
        OrderDetail orderDetail = orderDao.getDetail(orderId);
        orderDao.updateSkuStock(orderDetail.getOrderItems());

        return true;
    }

    @Override
    public Integer cancelTimeoutOrder() {
        Integer count = 0;
        var optional = orderSettingMapper.selectByPrimaryKey(1L);
        if (optional.isEmpty()) {
            return count;
        }
        // 查询超时、未支付的订单及订单详情
        List<OrderDetail> timeOutOrders = orderDao.getTimeOutOrders(optional.get().getNormalOrderOvertime());
        if (CollectionUtils.isEmpty(timeOutOrders)) {
            return count;
        }

        // 修改订单状态为交易取消
        List<Long> ids = new ArrayList<>();
        for (var timeOutOrder : timeOutOrders) {
            ids.add(timeOutOrder.getId());
        }
        orderDao.updateOrderStatus(ids, 4);
        for (var timeOutOrder : timeOutOrders) {
            // 解除订单商品库存锁定
            orderDao.releaseSkuStockLock(timeOutOrder.getOrderItems());
            // 修改优惠券使用状态
            updateCouponStatus(timeOutOrder.getCouponId(), timeOutOrder.getMemberId(), 0);
            // 返还使用积分
            if (timeOutOrder.getUseIntegration() != null) {
                UmsMember member = memberService.getById(timeOutOrder.getMemberId());
                memberService.updateIntegration(timeOutOrder.getMemberId(),
                        member.getIntegration() + timeOutOrder.getUseIntegration());
            }
        }
        return timeOutOrders.size();
    }

    @Override
    public void cancelOrder(Long orderId) {
        // 查询未付款的取消订单
        var optional = orderMapper.selectOne(c ->
                c.where(omsOrder.id, isEqualTo(orderId))
                        .and(omsOrder.status, isEqualTo(0))
                        .and(omsOrder.deleteStatus, isEqualTo(0)));
        if (optional.isEmpty()) {
            return;
        }

        OmsOrder cancelOrder = optional.get();

        //修改订单状态为取消
        cancelOrder.setStatus(4);
        int count = orderMapper.updateByPrimaryKeySelective(cancelOrder);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        List<OmsOrderItem> orderItems = orderItemMapper.select(c -> c.where(omsOrderItem.orderId, isEqualTo(orderId)));
        // 解除订单商品库存锁定
        if (!CollectionUtils.isEmpty(orderItems)) {
            orderDao.releaseSkuStockLock(orderItems);
        }

        // 修改优惠券使用状态
        updateCouponStatus(cancelOrder.getCouponId(), cancelOrder.getMemberId(), 0);

        //返还使用积分
        if (cancelOrder.getUseIntegration() != null) {
            UmsMember member = memberService.getById(cancelOrder.getMemberId());
            memberService.updateIntegration(cancelOrder.getMemberId(),
                    member.getIntegration() + cancelOrder.getUseIntegration());
        }
    }

    @Override
    public void cancelAsync(long orderId) {
        // 获取订单超时时间
        var optional = orderSettingMapper.selectByPrimaryKey(1L);
        if (optional.isEmpty()) {
            return;
        }
        long delayTimes = optional.get().getNormalOrderOvertime() * 60 * 1000;
        // 发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

    @Override
    public List<OrderDetail> list(Integer status, int pageIndex, int pageSize) {
        UmsMember member = memberService.getCurrentMember();
        List<OmsOrder> orders = orderMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = c.where(omsOrder.deleteStatus, isEqualTo(0))
                    .and(omsOrder.memberId, isEqualTo(member.getId()));
            if (status != -1) {
                whereDSL.and(omsOrder.status, isEqualTo(status));
            }
            c.orderBy(omsOrder.createTime.descending());

            // paging
            c.limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });

        if (orders.isEmpty()) {
            return Collections.emptyList();
        }

        // 设置数据信息
        List<Long> orderIds = orders.stream().map(OmsOrder::getId).collect(Collectors.toList());
        List<OmsOrderItem> orderItems = orderItemMapper.select(c -> c.where(omsOrderItem.orderId, isIn(orderIds)));
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (var order : orders) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(order, orderDetail);
            List<OmsOrderItem> relatedItemList = orderItems.stream()
                    .filter(item -> item.getOrderId().equals(orderDetail.getId())).collect(Collectors.toList());
            orderDetail.setOrderItems(relatedItemList);
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    @Override
    public OrderDetail detail(long orderId) {
        var optional = orderMapper.selectByPrimaryKey(orderId);
        if (optional.isEmpty()) {
            return null;
        }

        List<OmsOrderItem> items = orderItemMapper.select(c -> c.where(omsOrderItem.orderId, isEqualTo(orderId)));
        List<OmsOrderItem> orderItems = new ArrayList<>(items.size());
        for (OmsOrderItem item : items) {
            OrderItem oi = new OrderItem();
            BeanUtils.copyProperties(item, oi);
            oi.setReturnStatus(item.getReturnId() == null ? 0 : 1);
            orderItems.add(oi);
        }
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(optional.get(), orderDetail);
        orderDetail.setOrderItems(orderItems);
        return orderDetail;
    }

    @Override
    public Optional<OmsOrder> get(String orderSn) {
        return orderMapper.selectOne(c -> c.where(omsOrder.orderSn, isEqualTo(orderSn)));
    }

    @Override
    public void confirmOrderReceived(long orderId) {
        UmsMember member = memberService.getCurrentMember();
        var optional = orderMapper.selectByPrimaryKey(orderId);
        if (optional.isEmpty()) {
            return;
        }

        OmsOrder order = optional.get();

        Assert.ensure(member.getId().equals(order.getMemberId()), StandardCode.BAD_REQUEST.withMessage("不能确认他人订单！"));
        Assert.ensure(order.getStatus() == 2, StandardCode.BAD_REQUEST.withMessage("该订单还未发货！"));

        order.setStatus(3);
        order.setConfirmStatus(1);
        order.setReceiveTime(LocalDateTime.now());
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public void delete(long orderId) {
        UmsMember member = memberService.getCurrentMember();
        var optional = orderMapper.selectByPrimaryKey(orderId);
        if (optional.isEmpty()) {
            return;
        }

        OmsOrder order = optional.get();

        Assert.ensure(member.getId().equals(order.getMemberId()), StandardCode.BAD_REQUEST.withMessage("不能删除他人订单！"));
        Assert.ensure(order.getStatus() == 3 || order.getStatus() == 4,
                StandardCode.BAD_REQUEST.withMessage("该订单还未发货！"));

        if (order.getStatus() == 3 || order.getStatus() == 4) {
            order.setDeleteStatus(1);
            int count = orderMapper.updateByPrimaryKey(order);
            Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
        } else {
            Assert.failure(StandardCode.BAD_REQUEST.withMessage("只能删除已完成或已关闭的订单！"));
        }
    }

    /**
     * 生成18位订单编号:8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    private String generateOrderSn(OmsOrder order) {
        StringBuilder sb = new StringBuilder();
        String date = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
        String key = "mall:oms:orderId:" + date;
        int retry = 0;
        do {
            try (Jedis jedis = jedisPool.getResource()) {
                Long increment = jedis.incrBy(key, 1);
                sb.append(date);
                sb.append(String.format("%02d", order.getSourceType()));
                sb.append(String.format("%02d", order.getPayType()));
                String incrementStr = increment.toString();
                if (incrementStr.length() <= 6) {
                    sb.append(String.format("%06d", increment));
                } else {
                    sb.append(incrementStr);
                }
                return sb.toString();
            } catch (JedisConnectionException cause) {
                logger.error(cause.getMessage(), cause);

                retry++;
            }
        } while (retry <= 3);

        throw new ApiException("系统正忙，请稍后再试！");
    }

    /**
     * 删除下单商品的购物车信息
     */
    private void deleteCartItemList(List<PromotionCartItem> promotionCartItems, UmsMember currentMember) {
        List<Long> ids = new ArrayList<>();
        for (var cartPromotionItem : promotionCartItems) {
            ids.add(cartPromotionItem.getId());
        }
        cartItemService.delete(ids);
    }

    /**
     * 计算该订单赠送的成长值
     */
    private Integer calcGiftGrowth(List<OmsOrderItem> items) {
        int sum = 0;
        for (var item : items) {
            sum = sum + item.getGiftGrowth() * item.getProductQuantity();
        }
        return sum;
    }

    /**
     * 计算该订单赠送的积分
     */
    private Integer calcGifIntegration(List<OmsOrderItem> items) {
        int sum = 0;
        for (var item : items) {
            sum += item.getGiftIntegration() * item.getProductQuantity();
        }
        return sum;
    }

    /**
     * 将优惠券信息更改为指定状态
     *
     * @param couponId 优惠券id
     * @param memberId 会员id
     * @param useStatus 0->未使用；1->已使用
     */
    private void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        if (couponId == null) {
            return;
        }

        var couponHistories = couponHistoryMapper.select(c -> {
            c.where(smsCouponHistory.memberId, isEqualTo(memberId))
                    .and(smsCouponHistory.couponId, isEqualTo(couponId))
                    .and(smsCouponHistory.useStatus, isEqualTo(useStatus == 0 ? 1 : 0));
            return c;
        });

        Assert.ensure(couponHistories.size() > 0, String.format("用户[%d]没有领取优惠券[%d]", memberId, couponId));

        // 使用第一张优惠券
        var couponHistory = couponHistories.get(0);
        couponHistory.setUseTime(LocalDateTime.now());
        couponHistory.setUseStatus(useStatus);
        couponHistoryMapper.updateByPrimaryKeySelective(couponHistory);
    }

    private void handleRealAmount(List<OmsOrderItem> items) {
        items.forEach(item -> {
            // 原价-促销优惠-优惠券抵扣-积分抵扣
            BigDecimal realAmount = item.getProductPrice()
                    .subtract(item.getPromotionAmount())
                    .subtract(item.getCouponAmount())
                    .subtract(item.getIntegrationAmount());
            item.setRealAmount(realAmount);
        });
    }

    /**
     * 计算订单应付金额
     */
    private BigDecimal calcPayAmount(OmsOrder order) {
        //总金额+运费-促销优惠-优惠券优惠-积分抵扣
        return order.getTotalAmount()
                .add(order.getFreightAmount())
                .subtract(order.getPromotionAmount())
                .subtract(order.getCouponAmount())
                .subtract(order.getIntegrationAmount());
    }

    /**
     * 计算订单优惠券金额
     */
    private BigDecimal calcIntegrationAmount(List<OmsOrderItem> items) {
        BigDecimal integrationAmount = new BigDecimal(0);
        for (var item : items) {
            if (item.getIntegrationAmount() != null) {
                integrationAmount = integrationAmount
                        .add(item.getIntegrationAmount().multiply(new BigDecimal(item.getProductQuantity())));
            }
        }
        return integrationAmount;
    }

    /**
     * 计算总金额
     */
    private BigDecimal calcTotalAmount(List<OmsOrderItem> items) {
        BigDecimal totalAmount = new BigDecimal(0);
        for (var item : items) {
            totalAmount = totalAmount.add(item.getProductPrice().multiply(new BigDecimal(item.getProductQuantity())));
        }
        return totalAmount;
    }

    /**
     * 计算订单优惠券金额
     */
    private BigDecimal calcCouponAmount(List<OmsOrderItem> items) {
        BigDecimal couponAmount = new BigDecimal(0);
        for (var item : items) {
            if (item.getCouponAmount() != null) {
                couponAmount = couponAmount
                        .add(item.getCouponAmount().multiply(new BigDecimal(item.getProductQuantity())));
            }
        }
        return couponAmount;
    }

    /**
     * 获取订单促销信息
     */
    private String getPromotionInfo(List<OmsOrderItem> items) {
        StringBuilder sb = new StringBuilder();
        for (var item : items) {
            sb.append(item.getPromotionName());
            sb.append(";");
        }
        String result = sb.toString();
        if (result.endsWith(";")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 锁定下单商品的所有库存
     */
    private void lockStock(List<PromotionCartItem> items) {
        items.forEach(item -> skuStockMapper.selectByPrimaryKey(item.getProductSkuId())
                .ifPresent(stock -> {
                    stock.setLockStock(stock.getLockStock() + item.getQuantity());
                    skuStockMapper.updateByPrimaryKeySelective(stock);
                }));
    }

    /**
     * 计算订单活动优惠
     */
    private BigDecimal calcPromotionAmount(List<OmsOrderItem> items) {
        BigDecimal promotionAmount = new BigDecimal(0);
        for (var item : items) {
            if (item.getPromotionAmount() != null) {
                promotionAmount = promotionAmount
                        .add(item.getPromotionAmount().multiply(new BigDecimal(item.getProductQuantity())));
            }
        }
        return promotionAmount;
    }

    /**
     * 获取可用积分抵扣金额
     *
     * @param useIntegration 使用的积分数量
     * @param totalAmount 订单总金额
     * @param currentMember 使用的用户
     * @param hasCoupon 是否已经使用优惠券
     */
    private BigDecimal getUseIntegrationAmount(Integer useIntegration, BigDecimal totalAmount, UmsMember currentMember,
            boolean hasCoupon) {
        BigDecimal zeroAmount = new BigDecimal(0);
        // 判断用户是否有这么多积分
        if (useIntegration.compareTo(currentMember.getIntegration()) > 0) {
            return zeroAmount;
        }

        // 根据积分使用规则判断是否可用
        // 是否可与优惠券共用
        var optional = integrationConsumeSettingMapper.selectByPrimaryKey(1L);
        if (optional.isEmpty()) {
            return zeroAmount;
        }

        UmsIntegrationConsumeSetting integrationConsumeSetting = optional.get();

        if (hasCoupon && integrationConsumeSetting.getCouponStatus().equals(0)) {
            //不可与优惠券共用
            return zeroAmount;
        }
        //是否达到最低使用积分门槛
        if (useIntegration.compareTo(integrationConsumeSetting.getUseUnit()) < 0) {
            return zeroAmount;
        }
        //是否超过订单抵用最高百分比
        BigDecimal integrationAmount = new BigDecimal(useIntegration)
                .divide(new BigDecimal(integrationConsumeSetting.getUseUnit()), 2, RoundingMode.HALF_EVEN);
        BigDecimal maxPercent = new BigDecimal(integrationConsumeSetting.getMaxPercentPerOrder())
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
        if (integrationAmount.compareTo(totalAmount.multiply(maxPercent)) > 0) {
            return zeroAmount;
        }
        return integrationAmount;
    }

    /**
     * 对优惠券优惠进行处理
     *
     * @param items OmsOrderItem列表
     * @param couponHistoryDetail 可用优惠券详情
     */
    private void handleCouponAmount(List<OmsOrderItem> items, CouponHistoryDetail couponHistoryDetail) {
        SmsCoupon coupon = couponHistoryDetail.getCoupon();
        CouponUsage usage = CouponUsage.valueOf(coupon.getUseType());
        if (usage == CouponUsage.ALL_POWERFUL) {
            // 全场通用
            calcPerCouponAmount(items, coupon);
        } else if (usage == CouponUsage.SPECIFIED_CATEGORY) {
            // 指定分类
            List<OmsOrderItem> list = getCouponOrderItemByRelation(couponHistoryDetail, items, usage);
            calcPerCouponAmount(list, coupon);
        } else if (usage == CouponUsage.SPECIFIED_PRODUCT) {
            // 指定商品
            List<OmsOrderItem> couponOrderItemList = getCouponOrderItemByRelation(couponHistoryDetail, items, usage);
            calcPerCouponAmount(couponOrderItemList, coupon);
        }
    }

    /**
     * 对每个下单商品进行优惠券金额分摊的计算
     *
     * @param items 可用优惠券的下单商品商品
     */
    private void calcPerCouponAmount(List<OmsOrderItem> items, SmsCoupon coupon) {
        BigDecimal totalAmount = calcTotalAmount(items);
        for (var item : items) {
            //(商品价格/可用商品总价)*优惠券面额
            BigDecimal couponAmount = item.getProductPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN)
                    .multiply(coupon.getAmount());
            item.setCouponAmount(couponAmount);
        }
    }

    /**
     * 获取与优惠券有关系的下单商品
     *
     * @param couponHistoryDetail 优惠券详情
     * @param items 下单商品
     * @param usage 使用关系类型：1->相关分类；2->指定商品
     */
    private List<OmsOrderItem> getCouponOrderItemByRelation(
            CouponHistoryDetail couponHistoryDetail, List<OmsOrderItem> items, CouponUsage usage) {
        List<OmsOrderItem> result = new ArrayList<>();
        if (usage == CouponUsage.SPECIFIED_CATEGORY) {
            List<Long> categories = new ArrayList<>();
            for (var productCategoryRelation : couponHistoryDetail.getCategoryRelations()) {
                categories.add(productCategoryRelation.getProductCategoryId());
            }
            for (var item : items) {
                if (categories.contains(item.getProductCategoryId())) {
                    result.add(item);
                } else {
                    item.setCouponAmount(new BigDecimal(0));
                }
            }
        } else if (usage == CouponUsage.SPECIFIED_PRODUCT) {
            List<Long> products = new ArrayList<>();
            for (var productRelation : couponHistoryDetail.getProductRelations()) {
                products.add(productRelation.getProductId());
            }
            for (var item : items) {
                if (products.contains(item.getProductId())) {
                    result.add(item);
                } else {
                    item.setCouponAmount(new BigDecimal(0));
                }
            }
        }
        return result;
    }

    private CouponHistoryDetail getUseCoupon(List<PromotionCartItem> items, Long couponId) {
        var couponHistoryDetails = couponService.listCart(items, 1);
        for (var couponHistoryDetail : couponHistoryDetails) {
            if (couponHistoryDetail.getCoupon().getId().equals(couponId)) {
                return couponHistoryDetail;
            }
        }
        return null;
    }

    /**
     * 判断下单商品是否都有库存
     */
    private void ensureHasEnoughStock(List<PromotionCartItem> items) {
        for (var item : items) {
            if (item.getRealStock() == null || item.getRealStock() < item.getQuantity()) {
                Assert.failure(
                        StandardCode.BAD_REQUEST.withMessage(String.format("产品:[%s]库存不足，无法下单", item.getProductName())));
            }
        }
    }

    /**
     * 计算购物车中商品的价格
     */
    private ConfirmOrder.CalcAmount calcCartAmount(List<PromotionCartItem> items) {
        ConfirmOrder.CalcAmount calcAmount = new ConfirmOrder.CalcAmount();
        calcAmount.setFreightAmount(new BigDecimal(0));
        BigDecimal totalAmount = new BigDecimal("0");
        BigDecimal promotionAmount = new BigDecimal("0");
        for (var item : items) {
            totalAmount = totalAmount.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            promotionAmount = promotionAmount.add(item.getReduceAmount().multiply(new BigDecimal(item.getQuantity())));
        }
        calcAmount.setTotalAmount(totalAmount);
        calcAmount.setPromotionAmount(promotionAmount);
        calcAmount.setPayAmount(totalAmount.subtract(promotionAmount));
        return calcAmount;
    }
}