/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service.impl;

import static com.mall.mapper.OmsOrderDynamicSqlSupport.omsOrder;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import com.mall.oms.dao.OrderOperateHistoryDao;
import com.mall.oms.dto.OrderDetail;
import com.mall.oms.dto.OrderQueryParam;
import com.mall.oms.dto.ReceiverParam;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.core.HttpUtil;
import com.mall.mapper.OmsOrderMapper;
import com.mall.mapper.OmsOrderOperateHistoryMapper;
import com.mall.model.OmsOrder;
import com.mall.model.OmsOrderOperateHistory;
import com.mall.oms.dao.OrderDao;
import com.mall.oms.dto.MoneyParam;
import com.mall.oms.dto.OrderDeliveryParam;
import com.mall.oms.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OmsOrderMapper orderMapper;
    private final OmsOrderOperateHistoryMapper orderOperateHistoryMapper;
    private final OrderDao orderDao;
    private final OrderOperateHistoryDao orderOperateHistoryDao;

    public OrderServiceImpl(
            OmsOrderMapper orderMapper,
            OmsOrderOperateHistoryMapper orderOperateHistoryMapper,
            OrderDao orderDao,
            OrderOperateHistoryDao orderOperateHistoryDao) {
        this.orderMapper = orderMapper;
        this.orderOperateHistoryMapper = orderOperateHistoryMapper;
        this.orderDao = orderDao;
        this.orderOperateHistoryDao = orderOperateHistoryDao;
    }

    @Override
    public List<OmsOrder> list(OrderQueryParam queryParam, int pageIndex, int pageSize) {
        return orderDao.getList(queryParam, pageIndex * pageSize, pageSize);
    }

    @Override
    public void delivery(List<OrderDeliveryParam> deliveryParams) {
        // 批量发货
        int count = orderDao.delivery(deliveryParams);
        Assert.ensure(count == deliveryParams.size(), StandardCode.SQL_FAILURE);

        // 添加操作记录
        List<OmsOrderOperateHistory> histories = deliveryParams.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(LocalDateTime.now());
                    history.setOperateMan(HttpUtil.getUsername());
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        count = orderOperateHistoryDao.insertList(histories);
        Assert.ensure(count == deliveryParams.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void close(List<Long> ids, String note) {
        int count = orderMapper.update(c ->
                c.set(omsOrder.status).equalTo(4)
                        .where(omsOrder.id, isIn(ids))
                        .and(omsOrder.deleteStatus, isEqualTo(0)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);

        List<OmsOrderOperateHistory> histories = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(LocalDateTime.now());
            history.setOperateMan(HttpUtil.getUsername());
            history.setOrderStatus(4);
            history.setNote("订单关闭:" + note);
            return history;
        }).collect(Collectors.toList());
        count = orderOperateHistoryDao.insertList(histories);
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(List<Long> ids) {
        int count = orderMapper.update(c ->
                c.set(omsOrder.deleteStatus).equalTo(1)
                        .where(omsOrder.id, isIn(ids))
                        .and(omsOrder.deleteStatus, isEqualTo(0)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public OrderDetail detail(Long id) {
        return orderDao.getDetail(id);
    }

    @Override
    public void updateReceiver(ReceiverParam receiverParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverParam.getOrderId());
        order.setReceiverName(receiverParam.getReceiverName());
        order.setReceiverPhone(receiverParam.getReceiverPhone());
        order.setReceiverPostCode(receiverParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverParam.getReceiverProvince());
        order.setReceiverCity(receiverParam.getReceiverCity());
        order.setReceiverRegion(receiverParam.getReceiverRegion());
        order.setModifyTime(LocalDateTime.now());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        // 插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverParam.getOrderId());
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan(HttpUtil.getUsername());
        history.setOrderStatus(receiverParam.getStatus());
        history.setNote("修改收货人信息");
        count = orderOperateHistoryMapper.insert(history);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateMoney(MoneyParam moneyParam) {
        Optional<OmsOrder> optional = orderMapper.selectByPrimaryKey(moneyParam.getOrderId());
        Assert.ensure(optional.isPresent(), String.format("订单[%d]不存在", moneyParam.getOrderId()));
        OmsOrder oldOrder = optional.get();
        BigDecimal payAmount = oldOrder.getPayAmount();
        if (moneyParam.getFreightAmount() != null) {
            if (oldOrder.getFreightAmount() != null) {
                payAmount = payAmount.subtract(oldOrder.getFreightAmount());
            }
            payAmount = payAmount.add(moneyParam.getFreightAmount());
        }
        if (moneyParam.getDiscountAmount() != null) {
            if (oldOrder.getDiscountAmount() != null) {
                payAmount = payAmount.add(oldOrder.getDiscountAmount());
            }
            payAmount = payAmount.subtract(moneyParam.getDiscountAmount());
        }

        Assert.ensure(payAmount.compareTo(BigDecimal.ZERO) >= 0, "最终支付金额必须大于等于0");

        OmsOrder order = new OmsOrder();
        order.setId(moneyParam.getOrderId());
        order.setFreightAmount(moneyParam.getFreightAmount());
        order.setDiscountAmount(moneyParam.getDiscountAmount());
        order.setPayAmount(payAmount);
        order.setModifyTime(LocalDateTime.now());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        // 插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyParam.getOrderId());
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan(HttpUtil.getUsername());
        history.setOrderStatus(moneyParam.getStatus());
        history.setNote("修改费用信息");
        count = orderOperateHistoryMapper.insert(history);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(LocalDateTime.now());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan(HttpUtil.getUsername());
        history.setOrderStatus(status);
        history.setNote("修改备注信息：" + note);
        count = orderOperateHistoryMapper.insert(history);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }
}