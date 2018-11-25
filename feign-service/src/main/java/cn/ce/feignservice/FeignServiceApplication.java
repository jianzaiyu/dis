package cn.ce.feignservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableFeignClients
@EnableHystrixDashboard
@SpringBootApplication(scanBasePackages = {"cn.ce"})
@EnableEurekaClient
@EnableCircuitBreaker
@MapperScan("cn.ce.**.dao")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class FeignServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignServiceApplication.class, args);
    }
}
