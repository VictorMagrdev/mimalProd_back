package com.example.minimal_prod_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class GraphQLSecurityConfig {

    @Bean
    public Executor graphqlExecutor() {
        return new DelegatingSecurityContextExecutor(Executors.newCachedThreadPool());
    }
}
