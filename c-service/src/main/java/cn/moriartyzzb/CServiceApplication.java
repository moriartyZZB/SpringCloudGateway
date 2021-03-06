package cn.moriartyzzb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Zengzhibin
 * @title: AServiceApplication
 * @projectName SpringCloudGateway
 * @description: TODO
 * @date 2021/3/5 14:30
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CServiceApplication.class, args);
    }
}
