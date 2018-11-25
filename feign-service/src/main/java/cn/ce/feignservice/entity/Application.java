package cn.ce.feignservice.entity;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther ggs
 * @date 2018-08-13 11:04:14.738
 */
@Data
public class Application implements Serializable {
    private static final long serialVersionUID = 8219180401333092924L;
    /**
     * 能够独立生产的叫做应用。应用由各个业务线自己来定义
     */
    private Integer id;

    /**
     * 应用唯一标识
     */
    @NotBlank(message = "产品Code不能为空")
    private String applicationCode;

    @NotBlank(message = "产品名称不能为空")
    private String applicationName;

    /**
     * 应用图标url
     */
    private String icon;

    @NotNull(message = "发布状态不能为NULL")
    private Byte checkState;

    /**
     * 应用描述,可以为null，长度限制999
     */
    private String description;

    @NotBlank(message = "创建人Code不能为空")
    private String creatorCode;

    private Date createTime;

    //    @Value("#{T(java.time.LocalDateTime).now()}")
    private Date updateTime;

    private Integer supportDevice;

}