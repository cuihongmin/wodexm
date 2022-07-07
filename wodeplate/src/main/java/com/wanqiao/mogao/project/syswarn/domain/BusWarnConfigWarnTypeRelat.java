package com.wanqiao.mogao.project.syswarn.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

/**
 * 预警配置和预警方式关系表对象 bus_warn_config_warn_type_relat
 * 
 * @author cjj
 * @date 2021-05-10
 */
public class BusWarnConfigWarnTypeRelat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 预警类型id(动作) */
    @Excel(name = "预警类型id(动作)")
    private Long warnTypeId;

    /** 预警配置id */
    @Excel(name = "预警配置id")
    private Long warnConfigId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWarnTypeId(Long warnTypeId) 
    {
        this.warnTypeId = warnTypeId;
    }

    public Long getWarnTypeId() 
    {
        return warnTypeId;
    }
    public void setWarnConfigId(Long warnConfigId) 
    {
        this.warnConfigId = warnConfigId;
    }

    public Long getWarnConfigId() 
    {
        return warnConfigId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("warnTypeId", getWarnTypeId())
            .append("warnConfigId", getWarnConfigId())
            .toString();
    }
}
