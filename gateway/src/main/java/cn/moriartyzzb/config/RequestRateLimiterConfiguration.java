package cn.moriartyzzb.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @author Zengzhibin
 * @title: RequestRateLimiterConfiguration
 * @projectName SpringCloudGateway
 * @description: TODO
 * @date 2021/3/5 17:21
 */
@Configuration
public class RequestRateLimiterConfiguration {
    @Bean("apiKeyResolver")
    @Primary
    KeyResolver apiKeyResolver() {
        //按URL限流,即以每秒内请求数按URL分组统计，超出限流的url请求都将返回429状态
        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
    }

    @Bean("userKeyResolver")
    KeyResolver userKeyResolver() {
        //按用户限流
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }

    @Bean("ipKeyResolver")
    KeyResolver ipKeyResolver() {
        //按IP来限流
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
