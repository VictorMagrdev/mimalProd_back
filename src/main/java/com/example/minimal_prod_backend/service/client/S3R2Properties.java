package com.example.minimal_prod_backend.service.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cloudflare.r2")
public class S3R2Properties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
}

