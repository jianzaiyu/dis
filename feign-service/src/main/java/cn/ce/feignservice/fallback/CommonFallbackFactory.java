package cn.ce.feignservice.fallback;


import cn.ce.feignservice.service.CommonService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: ggs
 * @date: 2018-11-21 13:40
 **/
@Slf4j
@Component
public class CommonFallbackFactory implements FallbackFactory<CommonService> {
    @Override
    public CommonService create(Throwable throwable) {

        log.info("Feign + hystrix ,提供者服务挂了");

        return new CommonService() {
            @Override
            public String sayHiFromClient() {
                return "";
            }

            @Override
            public String sayHelloFromClient(String name) {
                return "";
            }
        };
    }
}
