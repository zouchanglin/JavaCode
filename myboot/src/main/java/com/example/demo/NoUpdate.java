package com.example.demo;

import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.web.support.InitializrMetadataUpdateStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoUpdate implements InitializrMetadataUpdateStrategy {
    @Bean
    public InitializrMetadataUpdateStrategy initializrMetadataUpdateStrategy() {
        return (metadata) -> metadata;
    }

    @Override
    public InitializrMetadata update(InitializrMetadata current) {
        return null;
    }
}