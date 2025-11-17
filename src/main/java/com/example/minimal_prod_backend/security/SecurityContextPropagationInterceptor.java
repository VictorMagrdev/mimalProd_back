package com.example.minimal_prod_backend.security;

import org.springframework.core.annotation.Order;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;
import reactor.core.publisher.Mono;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityContextPropagationInterceptor implements WebGraphQlInterceptor {

    @Override
    public @NonNull Mono<WebGraphQlResponse> intercept(
            @NonNull WebGraphQlRequest request,
            @NonNull Chain chain) {

        try {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                request.configureExecutionInput((executionInput, builder) ->
                        builder.graphQLContext(ctx -> ctx.put("authentication", authentication)).build());
            }
            return chain.next(request);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}