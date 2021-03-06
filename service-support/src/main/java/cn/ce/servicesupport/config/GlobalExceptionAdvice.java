package cn.ce.servicesupport.config;


import cn.ce.servicesupport.exception.BusinessException;
import cn.ce.servicesupport.pojo.Result;
import cn.ce.servicesupport.pojo.ResultCode;
import cn.ce.servicesupport.support.RequestLogSupport;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author ggs
 * @date 2018/8/5 13:09
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 前端传递参数 不符合规则抛出异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleParamsException(HttpServletRequest request,
                                        Exception ex) {
        RequestLogSupport.handleLog(request, ex);
        StringBuilder stringBuilder = new StringBuilder();
        BindingResult bindingResult = null;
        if (ex instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
        }
        if (ex instanceof BindException) {
            bindingResult = ((BindException) ex).getBindingResult();
        }
        if (bindingResult != null && bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                stringBuilder.append(error.getDefaultMessage()).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return new Result<>(HttpStatus.OK, ResultCode.SYS0002, new JSONArray(), stringBuilder.toString());
    }

    /**
     * 平台业务异常处理
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleBusinessException(HttpServletRequest request,
                                          BusinessException ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.OK, ResultCode.SYS0002, new JSONArray(), ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Result NoHandlerFoundException(HttpServletRequest request,
                                          NoHandlerFoundException ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.NOT_FOUND, ResultCode.SYS0000, new JSONArray(), ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleAllException(HttpServletRequest request,
                                     Exception ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.SYS0001, new JSONArray());
    }

}

