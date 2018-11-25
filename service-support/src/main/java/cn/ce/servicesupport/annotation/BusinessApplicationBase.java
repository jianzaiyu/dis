package cn.ce.servicesupport.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
@EnableHystrixDashboard
@SpringBootApplication(scanBasePackages = {"cn.ce"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@EnableEurekaClient
@EnableCircuitBreaker
@MapperScan("cn.ce.**.dao")
public @interface BusinessApplicationBase {

}
