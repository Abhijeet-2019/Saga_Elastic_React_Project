package com.segaExamples.eureka.apiGateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class LoggingFilter  extends AbstractGatewayFilterFactory<LoggingFilter.Config> {



    public LoggingFilter() {
        super(Config.class);  // ✅ Ensure correct handling of Config class
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("Incoming request from : {} ---  {}", request.getMethod(), request.getURI());
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Configuration properties (if any)
    }
}
