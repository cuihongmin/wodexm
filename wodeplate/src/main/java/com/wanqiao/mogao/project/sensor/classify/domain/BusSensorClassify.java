package com.wanqiao.mogao.project.sensor.classify.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 传感器类型对象 bus_sensor_classify
 * 
 * @author lixiangping
 * @date 2021-05-07
 */
public class BusSensorClassify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 传感器名称 */
    @Excel(name = "传感器名称")
    private String name;

    /** 数据采集频率 */
    @Excel(name = "数据采集频率")
    private Integer dataCollectCycle;

    /** 采集时间单位（s, m, h） */
    @Excel(name = "采集时间单位", readConverterExp = "s=,,m=,,h=")
    private String collectTimeUnit;

    /** 类型编码 */
    @Excel(name = "类型编码")
    private String code;

    /** 图标路径 */
    @Excel(name = "图标路径")
    private String path;

    /** 1启用 0禁用  默认为0 */
    @ApiModelProperty(hidden = true)
    @Excel(name = "1启用 0禁用  默认为0")
    private Integer state;

    /** $column.columnComment */
    @ApiModelProperty(hidden = true)
    @Excel(name = "1启用 0禁用  默认为0")
    private Long modifyBy;

    /** $column.columnComment */
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "1启用 0禁用  默认为0", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    public BusSensorClassify()
    {

    }

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
    public void setDataCollectCycle(Integer dataCollectCycle) 
    {
        this.dataCollectCycle = dataCollectCycle;
    }

    public Integer getDataCollectCycle() 
    {
        return dataCollectCycle;
    }
    public void setCollectTimeUnit(String collectTimeUnit) 
    {
        this.collectTimeUnit = collectTimeUnit;
    }

    public String getCollectTimeUnit() 
    {
        return collectTimeUnit;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
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
            .append("dataCollectCycle", getDataCollectCycle())
            .append("collectTimeUnit", getCollectTimeUnit())
            .append("code", getCode())
            .append("path", getPath())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("modifyBy", getModifyBy())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
