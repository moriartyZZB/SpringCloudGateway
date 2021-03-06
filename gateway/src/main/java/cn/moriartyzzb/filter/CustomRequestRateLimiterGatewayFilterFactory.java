package cn.moriartyzzb.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Zengzhibin
 * @title: CustomRequestRateLimiterGatewayFilterFactor
 * @projectName SpringCloudGateway
 * @description: TODO
 * @date 2021/3/5 17:43
 */
@Component
public class CustomRequestRateLimiterGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomRequestRateLimiterGatewayFilterFactory.Config> {

    public static final String KEY_RESOLVER_KEY = "keyResolver";

    private final RateLimiter defaultRateLimiter;
    private final KeyResolver defaultKeyResolver;

    public CustomRequestRateLimiterGatewayFilterFactory(RateLimiter defaultRateLimiter, @Qualifier("apiKeyResolver") KeyResolver defaultKeyResolver) {
        super(Config.class);
        this.defaultRateLimiter = defaultRateLimiter;
        this.defaultKeyResolver = defaultKeyResolver;
    }

    public KeyResolver getDefaultKeyResolver() {
        return defaultKeyResolver;
    }

    public RateLimiter getDefaultRateLimiter() {
        return defaultRateLimiter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public GatewayFilter apply(Config config) {
        KeyResolver resolver = (config.keyResolver == null) ? defaultKeyResolver : config.keyResolver;
        RateLimiter<Object> limiter = (config.rateLimiter == null) ? defaultRateLimiter : config.rateLimiter;
        return (exchange, chain) -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            return resolver.resolve(exchange).flatMap(key ->
                    // TODO: if key is empty?
                    limiter.isAllowed(route.getId(), key).flatMap(response -> {
                        for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                            exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
                        }
                        if (response.isAllowed()) {
                            return chain.filter(exchange);
                        }

                        ServerHttpResponse httpResponse = exchange.getResponse();
                        //返回json
                        httpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                        httpResponse.setStatusCode(config.getStatusCode());
                       // log.error("访问已限流，请稍候再请求");
                        Map<String, Object> resultMap = new HashMap<String, Object>();
                        resultMap.put("message","访问已限流，请稍候再请求");
                        resultMap.put("code","-1");
                        DataBuffer buffer = httpResponse.bufferFactory().wrap(JSON.toJSONBytes(resultMap));
                        return httpResponse.writeWith(Mono.just(buffer));
                    }));
        };
    }

    public static class Config {
        private KeyResolver keyResolver;
        private RateLimiter rateLimiter;
        private HttpStatus statusCode = HttpStatus.TOO_MANY_REQUESTS;

        public KeyResolver getKeyResolver() {
            return keyResolver;
        }

        public Config setKeyResolver(KeyResolver keyResolver) {
            this.keyResolver = keyResolver;
            return this;
        }
        public RateLimiter getRateLimiter() {
            return rateLimiter;
        }

        public Config setRateLimiter(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
            return this;
        }

        public HttpStatus getStatusCode() {
            return statusCode;
        }

        public Config setStatusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }
    }
}
