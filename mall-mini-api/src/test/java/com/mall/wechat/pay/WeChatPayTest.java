/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.pay;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mall.conf.WeChatConfiguration;
import com.mall.wechat.util.Json;
import org.junit.jupiter.api.Test;

class WeChatPayTest {

    @Test
    void prepareOrder() {
        WeChatConfiguration conf = new WeChatConfiguration();
        conf.getMerchant().setId("1604851009");
        conf.getMerchant().setCertSerialNo("2C18012EC6927711136D2CE200D938B949732E17");
        conf.getMerchant().setV3Key("e134c20381b4c13bb687db8bd8261b82");

        conf.getMiniProgram().setId("wx92615400cd9c6fb6");
        conf.getMiniProgram().setSecret("4528294e187130ca02b05c7fe847124c");

        WeChatPay wechatPay = new WeChatPay(conf);

        String json = "{\n"
                + "    \"time_expire\": \"2021-03-10T10:34:56+08:00\",\n"
                + "    \"amount\": {\n"
                + "        \"total\": 100,\n"
                + "        \"currency\": \"CNY\"\n"
                + "    },\n"
                + "    \"mchid\": \"1230000109\",\n"
                + "    \"description\": \"Image形象店-深圳腾大-QQ公仔\",\n"
                + "    \"notify_url\": \"https://www.weixin.qq.com/wxpay/pay.php\",\n"
                + "    \"payer\": {\n"
                + "        \"openid\": \"oqIsS5XaNeXhdlzIrUuV4YpN-vts\"\n"
                + "    },\n"
                + "    \"out_trade_no\": \"1217752501201407033233368018\",\n"
                + "    \"goods_tag\": \"WXG\",\n"
                + "    \"appid\": \"wxd678efh567hg6787\",\n"
                + "    \"attach\": \"自定义数据说明\",\n"
                + "    \"detail\": {\n"
                + "        \"invoice_id\": \"wx123\",\n"
                + "        \"goods_detail\": [\n"
                + "            {\n"
                + "                \"goods_name\": \"iPhoneX 256G\",\n"
                + "                \"wechatpay_goods_id\": \"1001\",\n"
                + "                \"quantity\": 1,\n"
                + "                \"merchant_goods_id\": \"商品编码\",\n"
                + "                \"unit_price\": 828800\n"
                + "            },\n"
                + "            {\n"
                + "                \"goods_name\": \"iPhoneX 256G\",\n"
                + "                \"wechatpay_goods_id\": \"1001\",\n"
                + "                \"quantity\": 1,\n"
                + "                \"merchant_goods_id\": \"商品编码\",\n"
                + "                \"unit_price\": 828800\n"
                + "            }\n"
                + "        ],\n"
                + "        \"cost_price\": 608800\n"
                + "    },\n"
                + "    \"scene_info\": {\n"
                + "        \"store_info\": {\n"
                + "            \"address\": \"广东省深圳市南山区科技中一道10000号\",\n"
                + "            \"area_code\": \"440305\",\n"
                + "            \"name\": \"腾讯大厦分店\",\n"
                + "            \"id\": \"0001\"\n"
                + "        },\n"
                + "        \"device_id\": \"013467007045764\",\n"
                + "        \"payer_client_ip\": \"14.23.150.211\"\n"
                + "    },\n"
                + "    \"settle_info\": {\n"
                + "        \"profit_sharing\": false\n"
                + "    }\n"
                + "}";

        Order order = Json.deserialize(json, Order.class);

        Payment payment = wechatPay.prepay(order);
        assertNotNull(payment);
        String pJson = com.mall.crosscut.util.Json.serialize(payment);
        assertNotNull(pJson);
    }
}