package cn.moriartyzzb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Zengzhibin
 * @title: cn.moriartyzzb.EurekaApplication
 * @projectName SpringCloudGateway
 * @description: TODO
 * @date 2021/3/5 14:21
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
