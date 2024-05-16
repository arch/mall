/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

public class PropertiesConfiguration implements EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfiguration.class);

    private static final String deployRoot="deployRoot";

    private Environment environment;

    @Override
    public void setEnvironment(@Nonnull Environment environment) {
        this.environment = environment;
    }

    /**
     * Support loading external application.properties file when production deployment.
     *
     * @return {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        final List<Resource> resources = new ArrayList<>();
        resources.add(new ClassPathResource("application.properties"));
        String base = System.getProperty(deployRoot);
        if (StringUtils.hasText(base)) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("the base dir is: %s", base));
            }
            String external = base + "/application.properties";
            File file = new File(external);
            if (file.exists() && !file.isDirectory()) {
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("using external conf: %s", external));
                }
                resources.add(new FileSystemResource(external));
            } else {
                logger.error(String.format("external conf: %s not exist", external));
            }
        } else if (logger.isDebugEnabled()) {
            logger.debug("not running from container or executable *.jar.");
        }

        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("The active profile is: %s", profile));
            }
            resources.add(new ClassPathResource(String.format("application-%s.properties", profile)));
            resources.add(new FileSystemResource(String.format("%s/application-%s.properties", base, profile)));
        }

        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setLocalOverride(true);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        pspc.setIgnoreResourceNotFound(true);
        pspc.setLocations(resources.toArray(new Resource[0]));

        return pspc;
    }
}