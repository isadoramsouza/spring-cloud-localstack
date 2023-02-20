package com.example.springcloudlocalstack.parameterstore.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ParameterStoreConfiguration {
    @Value("${name}")
    private String name;

    @Value("${days}")
    private String days;

    public String getName() {
        return name;
    }

    public String getDays() {
        return days;
    }
}

