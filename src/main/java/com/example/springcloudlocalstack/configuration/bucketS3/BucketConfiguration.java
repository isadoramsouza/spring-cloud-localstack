package com.example.springcloudlocalstack.configuration.bucketS3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class BucketConfiguration {
    @Value("${s3.bucket}")
    private String directory;

    public String getDirectory() {
        return directory;
    }
}

