/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.conf;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.util.Pool;

@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfiguration {
    private int database;
    private int timeout = 3000;
    private int maxIdle = 8;
    private int maxTotal = 100;
    private String user;
    private String password;
    private List<String> nodes;

    private final Sentinel sentinel = new Sentinel();

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    public Sentinel getSentinel() {
        return sentinel;
    }

    @Bean
    public Pool<Jedis> jedisPool() {
        // TODO: using redis 6.x ACL
        JedisPoolConfig conf = new JedisPoolConfig();
        conf.setMaxIdle(getMaxIdle());
        conf.setMaxTotal(getMaxTotal());

        if (sentinel.enable) {
            Set<String> sentinels = new HashSet<>(nodes);
            return new JedisSentinelPool(getSentinel().getMaster(), sentinels, conf, timeout, user, password, database);
        } else {
            HostAndPort hostAndPort = HostAndPort.parseString(nodes.get(0));
            return new JedisPool(conf, hostAndPort.getHost(), hostAndPort.getPort(), timeout, user, password, database);
        }
    }

    public static class Sentinel {
        private boolean enable;
        private String master;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getMaster() {
            return master;
        }

        public void setMaster(String master) {
            this.master = master;
        }
    }
}