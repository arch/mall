/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service.impl;

import static com.mall.mapper.OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply;
import static com.mall.mapper.OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import com.mall.oms.dto.OrderReturnApplyResult;
import com.mall.oms.dto.ReturnApplyQueryParam;
import com.mall.oms.dto.UpdateStatusParam;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.ReturnStatus;
import com.mall.mapper.OmsOrderReturnApplyMapper;
import com.mall.mapper.OmsOrderReturnReasonMapper;
import com.mall.model.OmsOrderReturnApply;
import com.mall.model.OmsOrderReturnReason;
import com.mall.oms.dao.OrderReturnDao;
import com.mall.oms.service.OrderReturnService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderReturnServiceImpl implements OrderReturnService {

    private final OmsOrderReturnApplyMapper returnApplyMapper;
    private final OmsOrderReturnReasonMapper returnReasonMapper;
    private final OrderReturnDao orderReturnDao;

    public OrderReturnServiceImpl(OmsOrderReturnApplyMapper returnApplyMapper,
            OmsOrderReturnReasonMapper returnReasonMapper, OrderReturnDao orderReturnDao) {
        this.returnApplyMapper = returnApplyMapper;
        this.returnReasonMapper = returnReasonMapper;
        this.orderReturnDao = orderReturnDao;
    }

    @Override
    public List<OmsOrderReturnApply> listApply(ReturnApplyQueryParam queryParam, int pageIndex, int pageSize) {
        return orderReturnDao.listApply(queryParam, pageIndex * pageSize, pageSize);
    }

    @Override
    public void deleteApply(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }

        int count = returnApplyMapper.delete(c ->
                c.where(omsOrderReturnApply.id, isIn(ids))
                        .and(omsOrderReturnApply.status, isEqualTo(3)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public OrderReturnApplyResult getApply(Long id) {
        return orderReturnDao.getApplyDetail(id);
    }

    @Override
    public void updateApplyStatus(Long id, UpdateStatusParam param) {
        ReturnStatus status = ReturnStatus.valueOf(param.getStatus());
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply().withId(id);
        if (status == ReturnStatus.RETURNING) {
            //确认退货
            returnApply.setStatus(1);
            returnApply.setReturnAmount(param.getReturnAmount());
            returnApply.setCompanyAddressId(param.getAddressId());
            returnApply.setHandleTime(LocalDateTime.now());
            returnApply.setHandleMan(param.getHandleMan());
            returnApply.setHandleNote(param.getHandleNote());
        } else if (status == ReturnStatus.FINISHED) {
            //完成退货
            returnApply.setStatus(2);
            returnApply.setReceiveTime(LocalDateTime.now());
            returnApply.setReceiveMan(param.getReceiveMan());
            returnApply.setReceiveNote(param.getReceiveNote());
        } else if (status == ReturnStatus.REFUSED) {
            //拒绝退货
            returnApply.setStatus(3);
            returnApply.setHandleTime(LocalDateTime.now());
            returnApply.setHandleMan(param.getHandleMan());
            returnApply.setHandleNote(param.getHandleNote());
        } else {
            return;
        }
        int count = returnApplyMapper.updateByPrimaryKeySelective(returnApply);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void createReason(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(LocalDateTime.now());
        int count = returnReasonMapper.insert(returnReason);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateReason(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        int count = returnReasonMapper.updateByPrimaryKey(returnReason);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void deleteReason(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }

        int count = returnReasonMapper.delete(c ->
                c.where(omsOrderReturnReason.id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public List<OmsOrderReturnReason> listReason(int pageIndex, int pageSize) {
        return returnReasonMapper.select(c -> {
            c.orderBy(omsOrderReturnReason.sort.descending())
                    .limit(pageSize).offset(pageIndex * pageSize);
            return c;
        });
    }

    @Override
    public OmsOrderReturnReason getReason(Long id) {
        return returnReasonMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public void updateReasonStatus(List<Long> ids, Integer status) {
        if (!status.equals(0) && !status.equals(1)) {
            return;
        }
        int count = returnReasonMapper.update(c ->
                c.set(omsOrderReturnReason.status).equalTo(status)
                        .where(omsOrderReturnReason.id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE);
    }
}