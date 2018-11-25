package cn.ce.servicesupport.config;

import cn.ce.servicesupport.pojo.Constants;
import cn.ce.servicesupport.support.PropertySupport;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ggs
 * @date 2018/8/5 16:17
 * @updateDate 20:40 2018/8/14/MKW 全局切面配置
 */


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private Environment environment;
    @Autowired
    private GlobalRequestInterceptor globalRequestInterceptor;
    /**
     * @Description 路径映射
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射文件访问路径
        registry.addResourceHandler(PropertySupport.getStaticAccessPath() + "**")
                .addResourceLocations("file:" + PropertySupport.getUploadFolder());//以后换成NAS
        //另一种思路
        //if (!ClassUtils.isPresent("cn.ce.pcpweb.config.SwaggerConfig", null)) {
        //Swagger访问路径（prod不映射）
        if (!Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            registry.addResourceHandler(Constants.swaggerUri.split(","))
                    .addResourceLocations(Constants.swaggerLocation.split(","));
        }
    }

    /**
     * @Description 拦截器配置
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(globalRequestInterceptor).addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns(PropertySupport.getStaticAccessPath() + "**");//以后换成NAS
        //Swagger访问路径（prod不拦截）
        if (!Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            interceptorRegistration.excludePathPatterns(Constants.swaggerPattern.split(","));
        }
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //1.需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat);
        // 不忽略对象属性中的null值
        // WriteNullListAsEmpty,
        // WriteNullStringAsEmpty);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //3、在convert中添加配置信息.
        converters.add(fastConverter);
    }

    /**
     * tomcat默认不解析PUT DELETE的BODY
     * 声明这个过滤器来解决 HttpPutFormContentFilter
     */
}
