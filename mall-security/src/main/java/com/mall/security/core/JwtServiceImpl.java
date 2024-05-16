/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.security.core;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.crosscut.util.HashUtil;

import java.util.Date;

public class JwtServiceImpl implements JwtService {
    private String secret;
    private int expiresIn;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public Jwt generate(String subject) throws Exception {
        // Create HMAC signer
        JWSSigner signer = new MACSigner(HashUtil.sha256(secret));

        // Prepare JWT with claims set
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .expirationTime(new Date(new Date().getTime() + expiresIn * 1000L))
                .issueTime(new Date())
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

        // Apply the HMAC protection
        signedJWT.sign(signer);

        // Serialize to compact form, produces something like
        // eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
        return Jwt.valueOf(signedJWT.serialize(), expiresIn);
    }

    @Override
    public String verify(String token) throws Exception {
        // parse the JWS and verify its HMAC
        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new MACVerifier(HashUtil.sha256(secret));
        if (!signedJWT.verify(verifier)) {
            throw new JwtInvalidException("TOKEN签名不合法");
        }

        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

        // verify whether timeout
        Assert.ensure(claimsSet.getExpirationTime().after(new Date()), StandardCode.INVALID_TOKEN);

        // Retrieve the JWT claims
        return claimsSet.getSubject();
    }
}