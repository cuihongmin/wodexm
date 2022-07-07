package com.wanqiao.mogao.project.detectionindextype.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

/**
 * 监测指标类型对象 bus_detection_index_type
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
public class BusDetectionIndexType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(hidden = true)
    private Long id;

    /** 监测指标类型名称 */
    @Excel(name = "监测指标类型名称")
    private String name;

    /** $column.columnComment */
    @Excel(name = "监测指标类型名称")
    private String reamark;

    /** $column.columnComment */
    @Excel(name = "监测指标类型名称")
    private Long modifyBy;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "监测指标类型名称", width = 30, dateFormat = "yyyy-MM-dd")
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
    public void setReamark(String reamark) 
    {
        this.reamark = reamark;
    }

    public String getReamark() 
    {
        return reamark;
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
            .append("reamark", getReamark())
            .append("createBy", getCreateBy())
            .append("modifyBy", getModifyBy())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
