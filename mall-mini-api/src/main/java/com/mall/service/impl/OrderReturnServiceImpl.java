/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import static com.mall.mapper.OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.service.MemberService;
import com.mall.service.OrderReturnService;
import com.mall.OrderStatus;
import com.mall.domain.ExpressParam;
import com.mall.domain.OrderReturnApplyParam;
import com.mall.domain.OrderReturnDetail;
import com.mall.domain.OrderReturnDetail.Address;
import com.mall.mapper.OmsCompanyAddressMapper;
import com.mall.mapper.OmsOrderItemMapper;
import com.mall.mapper.OmsOrderMapper;
import com.mall.mapper.OmsOrderReturnApplyMapper;
import com.mall.mapper.OmsOrderReturnReasonMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.model.OmsCompanyAddress;
import com.mall.model.OmsOrder;
import com.mall.model.OmsOrderItem;
import com.mall.model.OmsOrderReturnApply;
import com.mall.model.OmsOrderReturnReason;
import com.mall.model.UmsMember;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OrderReturnServiceImpl implements OrderReturnService {

    private final OmsOrderReturnApplyMapper orderReturnApplyMapper;
    private final OmsOrderReturnReasonMapper returnReasonMapper;
    private final OmsCompanyAddressMapper companyAddressMapper;
    private final OmsOrderMapper orderMapper;
    private final OmsOrderItemMapper orderItemMapper;
    private final MemberService memberService;

    public OrderReturnServiceImpl(OmsOrderReturnApplyMapper orderReturnApplyMapper,
            OmsOrderReturnReasonMapper returnReasonMapper,
            OmsCompanyAddressMapper companyAddressMapper, OmsOrderMapper orderMapper,
            OmsOrderItemMapper orderItemMapper, PmsProductMapper productMapper,
            MemberService memberService) {
        this.orderReturnApplyMapper = orderReturnApplyMapper;
        this.returnReasonMapper = returnReasonMapper;
        this.companyAddressMapper = companyAddressMapper;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.memberService = memberService;
    }


    @Override
    public void apply(OrderReturnApplyParam param) {
        UmsMember member = memberService.getCurrentMember();
        Assert.ensure(member != null, "必须登录才能发起退货操作");

        Optional<OmsOrder> optional = orderMapper.selectByPrimaryKey(param.getOrderId());
        Assert.ensure(optional.isPresent(), String.format("订单[%d]不存在", param.getOrderId()));
        OmsOrder order = optional.get();
        if (order.getStatus() == OrderStatus.UN_PAY.getStatus()) {
            Assert.failure("订单%s未付款");
        }

        Assert.ensure(Objects.equals(order.getMemberId(), member.getId()), "只能申请退款自己的订单");

        Optional<OmsOrderItem> orderItem = orderItemMapper.selectByPrimaryKey(param.getItemId());
        Assert.ensure(orderItem.isPresent(), String.format("订单项[%d]不存在", param.getItemId()));

        OmsOrderItem item = orderItem.get();
        Assert.ensure(Objects.equals(item.getOrderId(),param.getOrderId()),
                String.format("订单项[%d]不是订单[%d]的订单项", param.getItemId(), param.getOrderId()));

        Assert.ensure(item.getReturnId() == null, "退款单已经存在");

        OmsOrderReturnApply apply = new OmsOrderReturnApply();
        BeanUtils.copyProperties(param, apply);
        apply.setCreateTime(LocalDateTime.now());
        apply.setStatus(0);
        apply.setMemberId(member.getId());
        if (ObjectUtils.isEmpty(member.getUsername())) {
            apply.setMemberUsername(member.getNickname());
        } else {
            apply.setMemberUsername(member.getUsername());
        }
        BeanUtils.copyProperties(item, apply);
        apply.setReturnAmount(item.getRealAmount());
        apply.setProductCount(item.getProductQuantity());
        apply.setProductRealPrice(item.getRealAmount());

        int count = orderReturnApplyMapper.insert(apply);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        // 更新退款单号
        item.setReturnId(apply.getId());
        count = orderItemMapper.updateByPrimaryKeySelective(item);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public List<OmsOrderReturnReason> getReasons() {
        return returnReasonMapper.select(c -> c.orderBy(omsOrderReturnReason.sort.descending()));
    }

    @Override
    public OrderReturnDetail detail(long id) {
        UmsMember member = memberService.getCurrentMember();
        Assert.ensure(member != null, "必须登录才能发起退货操作");

        Optional<OmsOrderReturnApply> optional = orderReturnApplyMapper.selectByPrimaryKey(id);
        if (optional.isEmpty()) {
            return null;
        }

        OmsOrderReturnApply apply = optional.get();
        OrderReturnDetail detail = new OrderReturnDetail();
        BeanUtils.copyProperties(apply, detail);
        if (apply.getCompanyAddressId() != null) {
            Optional<OmsCompanyAddress> companyAddress = companyAddressMapper.selectByPrimaryKey(apply.getCompanyAddressId());
            companyAddress.ifPresent(ca -> {
                Address address = new Address();
                BeanUtils.copyProperties(ca, address);
                detail.setReturnAddress(address);
            });
        }

        return detail;
    }

    @Override
    public void setReturnExpress(long id, ExpressParam param) {
        OmsOrderReturnApply apply = new OmsOrderReturnApply()
                .withId(id)
                .withDeliveryCode(param.getDeliveryCode())
                .withDeliverySn(param.getDeliverySn());
        int count = orderReturnApplyMapper.updateByPrimaryKeySelective(apply);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }
}