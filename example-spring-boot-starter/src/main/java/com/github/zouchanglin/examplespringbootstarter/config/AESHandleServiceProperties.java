package com.github.zouchanglin.examplespringbootstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aes")
public class AESHandleServiceProperties {
    private Integer length = 128;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
