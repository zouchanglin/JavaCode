package com.github.zouchanglin.examplespringbootstarter.config;

import com.github.zouchanglin.examplespringbootstarter.service.AESHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(AESHandleServiceProperties.class)
public class AESHandleAutoConfiguration {
    @Autowired
    private AESHandleServiceProperties properties;

    @Bean
    AESHandleService aesHandleService() {
        return new AESHandleService(properties.getLength());
    }
}
