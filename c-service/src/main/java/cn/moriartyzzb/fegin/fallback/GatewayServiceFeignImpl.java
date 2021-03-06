package cn.moriartyzzb.fegin.fallback;

import cn.moriartyzzb.fegin.GatewayServiceFeign;
import org.springframework.stereotype.Component;

/**
 * @author Zengzhibin
 * @title: GatewayServiceFeign
 * @projectName SpringCloudGateway
 * @description: TODO
 * @date 2021/3/6 11:38
 */
@Component
public class GatewayServiceFeignImpl implements GatewayServiceFeign {
    public GatewayServiceFeignImpl() {

    }

    @Override
    public String hello() {
        return "fallback";
    }
}
