package cn.ce.feignservice.controller;

import cn.ce.feignservice.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ggs
 * @date: 2018-11-21 13:54
 **/
@RefreshScope
@RestController
@RequestMapping("name")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @Value("${name}")
    private String name;

    @GetMapping("{name}")
    public String home1(@PathVariable String name) {
        return commonService.sayHelloFromClient(name);
    }

    @GetMapping("trick")
    public String home() {
        return commonService.sayHiFromClient();
    }

    @GetMapping("hello")
    public String home2() {
        return this.name;
    }
}
