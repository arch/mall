/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.crosscut.exception.ApiException;
import com.mall.crosscut.util.Assert;
import com.mall.crosscut.util.Async;
import com.mall.crosscut.util.Json;
import com.mall.express.core.HttpClient;
import com.mall.express.core.HttpResult;
import com.mall.express.core.SignUtil;
import com.mall.express.request.QueryTrackParam;
import com.mall.express.request.QueryTrackReq;
import com.mall.express.response.QueryTrackData;
import com.mall.express.response.QueryTrackResp;
import com.mall.service.ExpressService;
import com.mall.mapper.OmsOrderMapper;
import com.mall.mapper.OmsOrderReturnApplyMapper;
import com.mall.model.OmsOrder;
import com.mall.model.OmsOrderReturnApply;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.util.Pool;

@Service
@ConfigurationProperties(prefix = "express")
public class ExpressServiceImpl implements ExpressService {

    private final static Logger logger = LoggerFactory.getLogger(ExpressServiceImpl.class);
    private final static long NEVER_EXPIRE = -1;
    private final static long EXPIRE_IN_SECONDS = 1800;
    private final static String KEY_FORMAT = "mall:express:%s";
    private final Pool<Jedis> jedisPool;

    private String customer;
    private String key;

    private final OmsOrderMapper orderMapper;
    private final OmsOrderReturnApplyMapper orderReturnApplyMapper;

    public ExpressServiceImpl(
            Pool<Jedis> jedisPool, OmsOrderMapper orderMapper, OmsOrderReturnApplyMapper orderReturnApplyMapper) {
        this.jedisPool = jedisPool;
        this.orderMapper = orderMapper;
        this.orderReturnApplyMapper = orderReturnApplyMapper;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public List<QueryTrackData> queryExpress(long orderId) {
        var optional = orderMapper.selectByPrimaryKey(orderId);
        if (optional.isEmpty()) {
            return null;
        }

        OmsOrder order = optional.get();

        Assert.ensure(StringUtils.hasText(order.getDeliveryCode()) && StringUtils.hasText(order.getDeliverySn()),
                "订单缺失快递信息，请先补充");

        List<QueryTrackData> list = queryCache(order.getDeliverySn());
        if (CollectionUtils.isEmpty(list)) {
            QueryTrackParam param = new QueryTrackParam();
            param.setCom(order.getDeliveryCode());
            param.setNum(order.getDeliverySn());
            param.setPhone(order.getReceiverPhone());
            list = queryApiAndCache(param);
        }
        return list;
    }

    @Override
    public List<QueryTrackData> queryReturnExpress(long id) {
        Optional<OmsOrderReturnApply> optional = orderReturnApplyMapper.selectByPrimaryKey(id);
        if (optional.isEmpty()) {
            return null;
        }

        OmsOrderReturnApply apply = optional.get();

        Assert.ensure(StringUtils.hasText(apply.getDeliveryCode()) && StringUtils.hasText(apply.getDeliverySn()),
                "订单缺失快递信息，请先补充");

        List<QueryTrackData> list = queryCache(apply.getDeliverySn());
        if (CollectionUtils.isEmpty(list)) {
            QueryTrackParam param = new QueryTrackParam();
            param.setCom(apply.getDeliveryCode());
            param.setNum(apply.getDeliverySn());
            param.setPhone(apply.getReturnPhone());
            list = queryApiAndCache(param);
        }
        return list;
    }

    private List<QueryTrackData> queryApiAndCache(QueryTrackParam queryTrackParam) {
        String param = Json.serialize(queryTrackParam);

        QueryTrackReq queryTrackReq = new QueryTrackReq();
        queryTrackReq.setParam(Json.serialize(queryTrackParam));
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtil.querySign(param, key, customer));

        try {
            HttpClient httpClient = new HttpClient();
            HttpResult httpResult = httpClient.execute(queryTrackReq);
            if (httpResult.getStatus() == HttpStatus.SC_OK) {
                String json = httpResult.getBody();
                QueryTrackResp resp = Json.deserialize(json, QueryTrackResp.class);
                if (resp != null) {
                    Assert.ensure(resp.isSuccess(), String.format("%s: %s", resp.getReturnCode(), resp.getMessage()));
                    // redis cache
                    Async.runAsync(() -> {
                        String key = String.format(KEY_FORMAT, queryTrackParam.getNum());
                        redisCache(key, json, resp.getState() == 3 ? NEVER_EXPIRE : EXPIRE_IN_SECONDS);
                    });
                    return resp.getData();
                }

                return Collections.emptyList();
            }

            return Collections.emptyList();
        } catch (Throwable cause) {
            throw new ApiException(cause);
        }
    }

    private List<QueryTrackData> queryCache(String deliverySn) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = String.format(KEY_FORMAT, deliverySn);
            String json = jedis.get(key);

            if (StringUtils.hasText(json)) {
                QueryTrackResp resp = Json.deserialize(json, QueryTrackResp.class);
                if (resp != null) {
                    return resp.getData();
                }

                return Collections.emptyList();
            }
        } catch (JedisConnectionException cause) {
            logger.error(cause.getMessage(), cause);
        }

        return Collections.emptyList();
    }

    private void redisCache(String key, String json, long expireSeconds) {
        if (!StringUtils.hasText(json)) {
            return;
        }

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, json);
            if (expireSeconds != -1) {
                jedis.expire(key, expireSeconds);
            }
        } catch (JedisConnectionException cause) {
            logger.error(cause.getMessage(), cause);
        }
    }
}