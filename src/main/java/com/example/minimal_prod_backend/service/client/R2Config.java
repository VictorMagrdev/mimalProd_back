package com.example.minimal_prod_backend.service.client;

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
            String missing = "";
            if (props.getEndpoint() == null || props.getEndpoint().isBlank()) {
                missing += "endpoint ";
            }
            if (props.getAccessKey() == null || props.getAccessKey().isBlank()) {
                missing += "accessKey ";
            }
            if (props.getSecretKey() == null || props.getSecretKey().isBlank()) {
                missing += "secretKey ";
            }
            if (props.getBucket() == null || props.getBucket().isBlank()) {
                missing += "bucket ";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException(
                        "Cloudflare R2 configuration error: missing properties -> " + missing.trim()
                );
            }
        return new CloudflareR2Client(
                props.getEndpoint(),
                props.getAccessKey(),
                props.getSecretKey(),
                props.getBucket()
        );
    }

}