package com.wanqiao.mogao.project.detectionindex.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

/**
 * 监测指标对象 bus_detection_index
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
public class BusDetectionIndex extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 指标名称 */
    @Excel(name = "指标名称")
    private String name;

    /** 优先级 */
    @Excel(name = "优先级")
    private Long priority;

    /** 监测指标类型id */
    @Excel(name = "监测指标类型id")
    private Long detectionIndexTypeId;

    /** $column.columnComment */
    @ApiModelProperty(hidden = true)
    @Excel(name = "监测指标类型id")
    private Long modifyBy;

    /** $column.columnComment */
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "监测指标类型id", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

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
    public void setPriority(Long priority) 
    {
        this.priority = priority;
    }

    public Long getPriority() 
    {
        return priority;
    }
    public void setDetectionIndexTypeId(Long detectionIndexTypeId) 
    {
        this.detectionIndexTypeId = detectionIndexTypeId;
    }

    public Long getDetectionIndexTypeId() 
    {
        return detectionIndexTypeId;
    }
    public void setModifyBy(Long modifyBy) 
    {
        this.modifyBy = modifyBy;
    }

    public Long getModifyBy() 
    {
        return modifyBy;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("priority", getPriority())
            .append("remark", getRemark())
            .append("detectionIndexTypeId", getDetectionIndexTypeId())
            .append("createBy", getCreateBy())
            .append("modifyBy", getModifyBy())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
