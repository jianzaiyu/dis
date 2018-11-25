package cn.ce.feignservice;

import cn.ce.servicesupport.annotation.BusinessApplicationBase;
import org.springframework.boot.SpringApplication;

@BusinessApplicationBase
public class FeignServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignServiceApplication.class, args);
    }
}
