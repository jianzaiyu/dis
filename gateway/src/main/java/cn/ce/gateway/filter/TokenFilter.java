package cn.ce.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class TokenFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 0; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() throws ZuulException {
        throw new ZuulException(new RuntimeException("111"),1,"11");
//        RequestContext ctx = RequestContext.getCurrentContext();
//        ctx.setThrowable(new RuntimeException("111"));
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        log.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());
//
//        String token = request.getParameter("token");// 获取请求的参数
//
//        if (StringUtils.isNotBlank(token)) {
//            ctx.setSendZuulResponse(true); //对请求进行路由
//            ctx.setResponseStatusCode(200);
//            ctx.set("isSuccess", true);
//            return null;
//        } else {
//            ctx.setSendZuulResponse(false); //不对其进行路由
//            ctx.setResponseStatusCode(400);
//            ctx.setResponseBody("token is empty");
//            ctx.set("isSuccess", false);
//        }
    }

}