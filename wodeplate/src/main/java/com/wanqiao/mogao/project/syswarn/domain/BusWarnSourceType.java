package com.wanqiao.mogao.project.syswarn.domain;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;

/**
 *
 * @author cjj
 * @date 2021-05-07
 */
@ApiModel(value = "警源类型： 人工预警，库水位预警、干滩预警、浸润预警等")
public class BusWarnSourceType implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String remark;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
