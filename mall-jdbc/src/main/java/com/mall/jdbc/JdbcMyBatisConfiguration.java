/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "jdbc")
@EnableTransactionManagement(proxyTargetClass = true)
public class JdbcMyBatisConfiguration {
    private final Jdbc master = new Jdbc();
    private final Jdbc slave  = new Jdbc();

    public Jdbc getMaster() {
        return master;
    }

    public Jdbc getSlave() {
        return slave;
    }

    @Bean(destroyMethod = "close")
    public JdbcRoutingDataSource getDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();

        targetDataSources.put(JdbcRouter.MASTER, getMaster().getDataSource());
        targetDataSources.put(JdbcRouter.SLAVE, getSlave().getDataSource());

        JdbcRoutingDataSource jdbcRoutingDataSource = new JdbcRoutingDataSource();
        jdbcRoutingDataSource.setTargetDataSources(targetDataSources);
        jdbcRoutingDataSource.setDefaultTargetDataSource(targetDataSources.get(JdbcRouter.MASTER));
        return jdbcRoutingDataSource;
    }

    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public static class Jdbc {
        private String driverName;
        private String driverUrl;
        private String username;
        private String password;

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverName() {
            return driverName;
        }

        public String getDriverUrl() {
            return driverUrl;
        }

        public void setDriverUrl(String driverUrl) {
            this.driverUrl = driverUrl;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public DataSource getDataSource() {
            HikariDataSource ds = new HikariDataSource();

            // setting pool size = cpu * 4
            int cpuCores = Runtime.getRuntime().availableProcessors();
            ds.setMaximumPoolSize(cpuCores * 4);

            ds.setDriverClassName(getDriverName());
            ds.setJdbcUrl(getDriverUrl());
            ds.setUsername(getUsername());
            ds.setPassword(getPassword());

            return ds;
        }
    }
}