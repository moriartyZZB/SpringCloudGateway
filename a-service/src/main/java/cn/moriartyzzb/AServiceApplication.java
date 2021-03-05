package cn.moriartyzzb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Zengzhibin
 * @title: AServiceApplication
 * @projectName SpringCloudGateway
 * @description: TODO
 * @date 2021/3/5 14:30
 */

@SpringBootApplication
@EnableEurekaClient
public class AServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AServiceApplication.class, args);
    }
}
