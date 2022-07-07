package com.wanqiao.mogao.project.syswarn.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;
import org.springframework.data.annotation.Transient;

/**
 * warn对象 bus_warn_recold
 * 
 * @author cjj
 * @date 2021-05-07
 */
public class BusWarnRecold  implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "预警时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预警时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date warnTime;

    @ApiModelProperty(value = "巡查预警,定量预警")
    @Excel(name = "巡查预警,定量预警")
    private String warnType;

    @ApiModelProperty(value = "预警级别1 红 3 橙 3黄 ")
    @Excel(name = "预警级别1 红 3 橙 3黄")
    private Integer warnLevel;

    @ApiModelProperty(value = "预警状态，1 预警,  0解除预警")
    private Integer warnState;

    /** 数据源头id */
    private Long dataSourceId;

    @Transient
    @ApiModelProperty(value = "预警源头")
    @Excel(name = "预警源头")
    private String warnSourceName;

    @ApiModelProperty(value = "预警源头id")
    private Long warnSourceId;

    /** 解除预警时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "解除预警时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date relieveWarnTime;

    @ApiModelProperty(value = "其他")
    @Excel(name = "其他")
    private String remark;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

   /* @Transient
    @ApiModelProperty(value = "开始时间", notes = "用于时间区间查询")
    private Date startTime;*/

    @Transient
    @JsonIgnore
    @ApiModelProperty(value = "开始时间", notes = "用于时间区间查询")
    private String beginTime;

    @Transient
    @JsonIgnore
    @ApiModelProperty(value = "结束时间", notes = "用于时间区间查询")
    private String endTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWarnTime(Date warnTime) 
    {
        this.warnTime = warnTime;
    }

    public Date getWarnTime() 
    {
        return warnTime;
    }
    public void setWarnType(String warnType) 
    {
        this.warnType = warnType;
    }

    public String getWarnType() 
    {
        return warnType;
    }
    public void setWarnLevel(Integer warnLevel) 
    {
        this.warnLevel = warnLevel;
    }

    public Integer getWarnLevel() 
    {
        return warnLevel;
    }
    public void setWarnState(Integer warnState) 
    {
        this.warnState = warnState;
    }

    public Integer getWarnState() 
    {
        return warnState;
    }
    public void setDataSourceId(Long dataSourceId)
    {
        this.dataSourceId = dataSourceId;
    }

    public Long getDataSourceId() 
    {
        return dataSourceId;
    }
    public void setRelieveWarnTime(Date relieveWarnTime) 
    {
        this.relieveWarnTime = relieveWarnTime;
    }

    public Date getRelieveWarnTime() 
    {
        return relieveWarnTime;
    }

    public String getWarnSourceName() {
        return warnSourceName;
    }

    public void setWarnSourceName(String warnSourceName) {
        this.warnSourceName = warnSourceName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getWarnSourceId() {
        return warnSourceId;
    }

    public void setWarnSourceId(Long warnSourceId) {
        this.warnSourceId = warnSourceId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
