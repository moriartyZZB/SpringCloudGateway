package cn.moriartyzzb.fegin;

import cn.moriartyzzb.fegin.fallback.GatewayServiceFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Zengzhibin
 * @title: GatewayServiceFegin
 * @projectName SpringCloudGateway
 * @description: TODO
 * @date 2021/3/6 10:23
 */
//value注册中心中的实例名称，path对应B服务里面的
//如果有多个同名服务，可以指定IP，默认时候轮询
@Component
@FeignClient(value = "gateway-service",url = "http://localhost:9000", fallback = GatewayServiceFeignImpl.class)
public interface GatewayServiceFeign {

    /**
     * 获取所有对象
     * 这就是在fegin中，为我们目标服务中的方法定义的接口，客户端直接通过API代码调用这个类，
     * fegin会自动通过http协议调用目标服务中该接口的实现方法。
     * 这样我们就避免了http的解析和封装操作，提高编码效率。
     **/
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    String hello() ;

}
