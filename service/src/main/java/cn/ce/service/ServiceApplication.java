package cn.ce.service;

import cn.ce.servicesupport.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@EnableEurekaClient
@RestController
@RequestMapping("name")
public class ServiceApplication {
    @Value("${server.port}")
    String port;

    @GetMapping("trick")
    public String home() throws HttpRequestMethodNotSupportedException {
        throw new HttpRequestMethodNotSupportedException("底层Service来捣乱");
    }

    @GetMapping("{name}")
    public String home1(@PathVariable String name) {
        return "Hello world " + name + " from " + port;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
