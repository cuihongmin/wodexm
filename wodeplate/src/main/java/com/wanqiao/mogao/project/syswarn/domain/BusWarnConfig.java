package com.wanqiao.mogao.project.syswarn.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

/**
 * 预警设置对象 bus_warn_config
 * 
 * @author cjj
 * @date 2021-05-10
 */
@ApiModel(value = "预警配置")
public class BusWarnConfig implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "预警级别1 红  2 橙 3黄")
    private Integer warnLevel;

    @ApiModelProperty(value = "所属分类 1传感器自动采集  0 人工")
    private Integer type;

    @ApiModelProperty("预警类型")
    private String name;

    private String remak;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Transient
    @ApiModelProperty(value = "报警方式")
    private List<BusAlarmWay> alarmWay;

    @Transient
    @ApiModelProperty(value = "安全人员")
    private List<BusSafetyPeople> safetyPeople;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWarnLevel(Integer warnLevel) 
    {
        this.warnLevel = warnLevel;
    }

    public Integer getWarnLevel() 
    {
        return warnLevel;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setRemak(String remak) 
    {
        this.remak = remak;
    }

    public String getRemak() 
    {
        return remak;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public List<BusAlarmWay> getAlarmWay() {
        return alarmWay;
    }

    public void setAlarmWay(List<BusAlarmWay> alarmWay) {
        this.alarmWay = alarmWay;
    }

    public List<BusSafetyPeople> getSafetyPeople() {
        return safetyPeople;
    }

    public void setSafetyPeople(List<BusSafetyPeople> safetyPeople) {
        this.safetyPeople = safetyPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
