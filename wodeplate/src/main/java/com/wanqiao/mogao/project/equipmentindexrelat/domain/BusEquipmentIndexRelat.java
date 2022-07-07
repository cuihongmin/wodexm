package com.wanqiao.mogao.project.equipmentindexrelat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

/**
 * 设备和指标关联关系对象 bus_equipment_index_relat
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
public class BusEquipmentIndexRelat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 传感器id */
    @Excel(name = "传感器id")
    private String sensorId;

    /** 指标id */
    @Excel(name = "指标id")
    private Long indexId;

    /** 相同code的传感器一起计算同一个指标 */
    @Excel(name = "相同code的传感器一起计算同一个指标")
    private String code;

    /** $column.columnComment */
    @Excel(name = "相同code的传感器一起计算同一个指标")
    private Integer priority;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSensorId(String sensorId) 
    {
        this.sensorId = sensorId;
    }

    public String getSensorId() 
    {
        return sensorId;
    }
    public void setIndexId(Long indexId) 
    {
        this.indexId = indexId;
    }

    public Long getIndexId() 
    {
        return indexId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setPriority(Integer priority) 
    {
        this.priority = priority;
    }

    public Integer getPriority() 
    {
        return priority;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sensorId", getSensorId())
            .append("indexId", getIndexId())
            .append("code", getCode())
            .append("priority", getPriority())
            .toString();
    }
}
