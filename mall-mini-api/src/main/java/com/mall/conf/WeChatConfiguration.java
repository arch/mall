/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.conf;

import com.mall.wechat.api.WeChatClient;
import com.mall.wechat.pay.WeChatPay;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfiguration {
    private final MiniProgram miniProgram = new MiniProgram();
    private final Merchant merchant = new Merchant();

    public MiniProgram getMiniProgram() {
        return miniProgram;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    @Bean
    public WeChatClient wechatClient() {
        return new WeChatClient(this);
    }

    @Bean
    public WeChatPay wechatPay() {
        return new WeChatPay(this);
    }

    public static class MiniProgram {
        private String id;
        private String secret;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

    public static class Merchant {
        private String id;
        private String certSerialNo;
        private String v3Key;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCertSerialNo() {
            return certSerialNo;
        }

        public void setCertSerialNo(String certSerialNo) {
            this.certSerialNo = certSerialNo;
        }

        public String getV3Key() {
            return v3Key;
        }

        public void setV3Key(String v3Key) {
            this.v3Key = v3Key;
        }
    }
}