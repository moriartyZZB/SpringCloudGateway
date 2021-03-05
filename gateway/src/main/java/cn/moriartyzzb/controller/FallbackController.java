package cn.moriartyzzb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zengzhibin
 * @title: FallbackController
 * @projectName SpringCloudGateway
 * @description: TODO
 * @date 2021/3/5 15:31
 */
@RestController
public class FallbackController {


    /*
     * @ClassName FallbackController
     * @Desc TODO   网关断路器
     * @Date 2019/6/23 19:35
     * @Version 1.0
     */
    @RequestMapping("/fallback")
    public String fallback() {
        return "I'm Spring Cloud Gateway fallback.";
    }

}
