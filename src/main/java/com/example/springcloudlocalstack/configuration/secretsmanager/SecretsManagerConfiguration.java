package com.example.springcloudlocalstack.configuration.secretsmanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class SecretsManagerConfiguration {
    @Value("${value1}")
    private String value1;

    @Value("${value2}")
    private String value2;

    @Value("${value3}")
    private String value3;

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public String getValue3() {
        return value3;
    }
}

