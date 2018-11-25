package cn.ce.servicesupport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ggs
 * @date 2018/8/5 16:17
 * @updateDate 20:40 2018/8/14/MKW
 */
@Configuration
@EnableSwagger2
//@Profile({"dev", "local", "pre"})//在生产环境不开启
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("ticket")
                .description("user ticket")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build(); //添加全局的header参数
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.ce"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DIS平台接口文档")
//                .description("restful风格接口")
//                .contact(new Contact("ggs", "wiki.300.cn/gongshuyang", "gongshuyang@300.cn"))
//                .version("1.0")
                .build();
    }
}
