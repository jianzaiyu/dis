package cn.ce.feignservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: ggs
 * @date: 2018-11-21 10:48
 **/
@FeignClient(value = "service"/*,fallback = CommonFallback.class,fallbackFactory = CommonFallbackFactory.class*/)
@Service
public interface CommonService {
    @GetMapping("name/trick")
    String sayHiFromClient();

    @GetMapping("name/{name}")
    String sayHelloFromClient(@PathVariable(value = "name") String name);
}
