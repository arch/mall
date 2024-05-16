/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.http;

import com.mall.wechat.core.Credentials;
import com.mall.wechat.core.Validator;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.execchain.ClientExecChain;

public class SignatureExec implements ClientExecChain {
    private final ClientExecChain mainExec;
    private final Credentials credentials;
    private final Validator validator;

    SignatureExec(Credentials credentials, Validator validator, ClientExecChain mainExec) {
        this.credentials = credentials;
        this.validator = validator;
        this.mainExec = mainExec;
    }

    @Override
    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request,
            HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        if (request.getURI().getHost().endsWith(".mch.weixin.qq.com")) {
            return executeWithSignature(route, request, context, execAware);
        } else {
            return mainExec.execute(route, request, context, execAware);
        }
    }

    protected void convertToRepeatableRequestEntity(HttpRequestWrapper request) throws IOException {
        if (request instanceof HttpEntityEnclosingRequest) {
            HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
            if (entity != null) {
                ((HttpEntityEnclosingRequest) request).setEntity(new BufferedHttpEntity(entity));
            }
        }
    }

    protected void convertToRepeatableResponseEntity(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            response.setEntity(new BufferedHttpEntity(entity));
        }
    }

    private CloseableHttpResponse executeWithSignature(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        // 上传类不需要消耗两次故不做转换
        if (!(request.getOriginal() instanceof WechatPayUploadHttpPost)) {
            convertToRepeatableRequestEntity(request);
        }
        // 添加认证信息
        request.addHeader("Authorization", credentials.getSchema() + " " + credentials.getToken(request));

        // 执行
        CloseableHttpResponse response = mainExec.execute(route, request, context, execAware);

        // 对成功应答验签
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() < 300) {
            convertToRepeatableResponseEntity(response);
            if (!validator.validate(response)) {
                throw new HttpException("应答的微信支付签名验证失败");
            }
        }
        return response;
    }
}