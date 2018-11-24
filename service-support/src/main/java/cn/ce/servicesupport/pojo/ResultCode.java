package cn.ce.servicesupport.pojo;



/**
 * @author ggs
 * @date 2018/8/5 11:09
 * @update 2018/8/13/mkw 项目所有对外rest接口返回状态码定义
 */

public enum ResultCode {

    //系统相关状态码
    /**
     * 状态码定义规则：业务正常返回SYS+两位模块标识+状态码标识
     * 业务异常返回ERROR+两位模块标识+状态码标识
     * 如：用户模块标识为01，用户模块的第一个状态码为01 则状态码为SYS0101
     */
    SYS0000("系统正常")
    ,SYS0001("系统错误")
    ,SYS0002("业务错误");

    private String desc;

    ResultCode(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }

    public String getCode(){
        return this.toString();
    }
}
