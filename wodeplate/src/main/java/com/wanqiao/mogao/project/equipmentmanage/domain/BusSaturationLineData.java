package com.wanqiao.mogao.project.equipmentmanage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;
import org.springframework.data.annotation.Transient;

/**
 * 浸润线通过渗压计采集数据计算的来对象 bus_saturation_line_data
 * 
 * @author cjj
 * @date 2021-05-11
 */
@ApiModel(value = "浸润线 采集数据 ")
public class BusSaturationLineData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "水温")
    @Excel(name = "温度")
    private BigDecimal temperature;

    @ApiModelProperty(value = "水深")
    @Excel(name = "水深")
    private BigDecimal deep;

    @ApiModelProperty(value = "压力")
    @Excel(name = "压力")
    private BigDecimal pressure;

    @ApiModelProperty(value = "指标id")
    private Long indexId;

    @Transient
    @ApiModelProperty(value = "指标名称")
    private String indexName;

    @ApiModelProperty(value = "传感器渗压计id")
    @Excel(name = "传感器渗压计id")
    private Long osmometerId;

    @Transient
    @ApiModelProperty(value = "传感器名称")
    private String sensorName;


    /** 浸润线值(实际值) */
    @Excel(name = "浸润线值(实际值)")
    private BigDecimal saturationLineValue;

    /** 浸润线值(校验值) */
    @Excel(name = "浸润线值(校验值)")
    private BigDecimal saturationLineVerifyValue;

    /** 线长 */
    @Excel(name = "线长")
    private BigDecimal lineLength;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "线长", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /** $column.columnComment */
    @Excel(name = "线长")
    private Long modifyBy;

    /** 1 红2橙 2黄     0不 预警 */
    @Excel(name = "1 红2橙 2黄     0不 预警")
    private Integer warn;

    @Transient
    @ApiModelProperty(value = "黄色报警值")
    @Excel(name = "黄色报警")
    private BigDecimal yellowAlarm;

    @Transient
    @ApiModelProperty(value = "红色报警值")
    @Excel(name = "红色报警")
    private BigDecimal redAlarm;

    @Transient
    @ApiModelProperty(value = "橙色报警值")
    @Excel(name = "橙色报警")
    private BigDecimal orangeAlarm;

    @Transient
    @ApiModelProperty(value = "检测类型名称")
    private String detectionTypeName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTemperature(BigDecimal temperature) 
    {
        this.temperature = temperature;
    }

    public BigDecimal getTemperature() 
    {
        return temperature;
    }
    public void setDeep(BigDecimal deep) 
    {
        this.deep = deep;
    }

    public BigDecimal getDeep() 
    {
        return deep;
    }
    public void setPressure(BigDecimal pressure) 
    {
        this.pressure = pressure;
    }

    public BigDecimal getPressure() 
    {
        return pressure;
    }
    public void setIndexId(Long indexId) 
    {
        this.indexId = indexId;
    }

    public Long getIndexId() 
    {
        return indexId;
    }
    public void setOsmometerId(Long osmometerId) 
    {
        this.osmometerId = osmometerId;
    }

    public Long getOsmometerId() 
    {
        return osmometerId;
    }
    public void setSaturationLineValue(BigDecimal saturationLineValue) 
    {
        this.saturationLineValue = saturationLineValue;
    }

    public BigDecimal getSaturationLineValue() 
    {
        return saturationLineValue;
    }
    public void setSaturationLineVerifyValue(BigDecimal saturationLineVerifyValue) 
    {
        this.saturationLineVerifyValue = saturationLineVerifyValue;
    }

    public BigDecimal getSaturationLineVerifyValue() 
    {
        return saturationLineVerifyValue;
    }
    public void setLineLength(BigDecimal lineLength) 
    {
        this.lineLength = lineLength;
    }

    public BigDecimal getLineLength() 
    {
        return lineLength;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }
    public void setModifyBy(Long modifyBy) 
    {
        this.modifyBy = modifyBy;
    }

    public Long getModifyBy() 
    {
        return modifyBy;
    }
    public void setWarn(Integer warn) 
    {
        this.warn = warn;
    }

    public Integer getWarn() 
    {
        return warn;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public BigDecimal getYellowAlarm() {
        return yellowAlarm;
    }

    public void setYellowAlarm(BigDecimal yellowAlarm) {
        this.yellowAlarm = yellowAlarm;
    }

    public BigDecimal getRedAlarm() {
        return redAlarm;
    }

    public void setRedAlarm(BigDecimal redAlarm) {
        this.redAlarm = redAlarm;
    }

    public BigDecimal getOrangeAlarm() {
        return orangeAlarm;
    }

    public void setOrangeAlarm(BigDecimal orangeAlarm) {
        this.orangeAlarm = orangeAlarm;
    }

    public String getDetectionTypeName() {
        return detectionTypeName;
    }

    public void setDetectionTypeName(String detectionTypeName) {
        this.detectionTypeName = detectionTypeName;
    }
}
