package com.example.minimal_prod_backend.security;

import com.example.minimal_prod_backend.service.impl.CloudflareR2Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class R2Config {
    private final S3R2Properties props;

    public R2Config(S3R2Properties props) {
        this.props = props;
    }

    @Bean
    public CloudflareR2Client r2Client() {
        return new CloudflareR2Client(
                props.getEndpoint(),
                props.getAccessKey(),
                props.getSecretKey()
        );
    }
}