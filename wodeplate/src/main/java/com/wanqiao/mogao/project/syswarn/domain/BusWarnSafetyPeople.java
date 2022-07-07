package com.wanqiao.mogao.project.syswarn.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

/**
 * 预警配置和安全员关系表对象 bus_warn_safety_people
 * 
 * @author cjj
 * @date 2021-05-10
 */
public class BusWarnSafetyPeople extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 预警配置id */
    @Excel(name = "预警配置id")
    private Long configId;

    /** 安全员id */
    @Excel(name = "安全员id")
    private Long safetyBy;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setConfigId(Long configId) 
    {
        this.configId = configId;
    }

    public Long getConfigId() 
    {
        return configId;
    }
    public void setSafetyBy(Long safetyBy) 
    {
        this.safetyBy = safetyBy;
    }

    public Long getSafetyBy() 
    {
        return safetyBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("configId", getConfigId())
            .append("safetyBy", getSafetyBy())
            .toString();
    }
}
