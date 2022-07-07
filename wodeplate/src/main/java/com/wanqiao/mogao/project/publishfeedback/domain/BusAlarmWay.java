package com.wanqiao.mogao.project.publishfeedback.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;

/**
 * publishfeedback对象 bus_alarm_way
 * 
 * @author cjj
 * @date 2021-05-07
 */
@ApiModel(value = "报警方式", description = "报警方式短信、邮件等")
public class BusAlarmWay implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(name = "报警方式编码")
    private String code;

    @ApiModelProperty(name = "报警方式名称")
    private String name;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
}
