package cn.ce.feignservice.fallback;

import cn.ce.feignservice.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: ggs
 * @date: 2018-11-21 14:32
 **/
@Slf4j
@Component
public class CommonFallback implements CommonService {
    @Override
    public String sayHiFromClient() {
        log.info("Feign + hystrix ,提供者服务挂了");
        return "trick";
    }

    @Override
    public String sayHelloFromClient(String name) {
        log.info("Feign + hystrix ,提供者服务挂了");
        return "";
    }
}
