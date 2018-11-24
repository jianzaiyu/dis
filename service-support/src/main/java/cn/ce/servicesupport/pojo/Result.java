package cn.ce.servicesupport.pojo;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author ggs
 * @date 2018/8/5 11:07
 */
@Data
public class Result<T>{
    private int status;
    private String code;
    private String msg;
    private T data;


    public Result() {

    }

    public Result(HttpStatus status, ResultCode resultCode) {
        this.status = status.value();
        this.code = resultCode.getCode();
        this.msg = resultCode.getDesc();
    }

    public Result(HttpStatus status, ResultCode resultCode, T data) {
        this.status = status.value();
        this.code = resultCode.getCode();
        this.msg = resultCode.getDesc();
        this.data = data;
    }

    public Result(HttpStatus status, ResultCode resultCode, T data, String msg) {
        this.status = status.value();
        this.code = resultCode.getCode();
        this.msg = msg;
        this.data = data;
    }

}
