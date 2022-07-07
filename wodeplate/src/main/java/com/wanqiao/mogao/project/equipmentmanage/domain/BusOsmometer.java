package com.wanqiao.mogao.project.equipmentmanage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;
import org.springframework.data.annotation.Transient;

/**
 * 渗压计对象 bus_osmometer
 * 
 * @author cjj
 * @date 2021-05-10
 */
@ApiModel(value = "设备：渗压计实体")
public class BusOsmometer
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "设备名称")
    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "群组id")
    @Excel(name = "群组id")
    private Long groupId;

    @ApiModelProperty(value = "群组名称")
    @Transient
    private String groupName;

    @ApiModelProperty(value = "群组ip地址")
    @Transient
    private String groupIp;

    @ApiModelProperty(value = "设备地址")
    @Excel(name = "设备地址")
    private Long address;

    @Excel(name = "厂家")
    @ApiModelProperty(value = "厂家")
    private String company;

    @ApiModelProperty(value = "设备编号")
    @Excel(name = "设备编号")
    private String code;

    @Excel(name = "坐标x")
    @ApiModelProperty(value = "坐标x")
    private BigDecimal x;

    @ApiModelProperty(value = "坐标z")
    @Excel(name = "坐标z")
    private BigDecimal z;

    /** 纵坐标 */
    @Excel(name = "坐标y")
    @ApiModelProperty(value = "坐标y")
    private BigDecimal y;

    @ApiModelProperty(value = "单位")
    @Excel(name = "单位")
    private String unit;

    @ApiModelProperty(value = "线长", notes = "浸润线=线长-测试长度")
    @Excel(name = "线长", readConverterExp = "浸润线=线长-测试长度")
    private BigDecimal lineLength;

    @ApiModelProperty(value = "设备类型")
    private Long sensorClassifyId;

    @Excel(name = "设备类型")
    @ApiModelProperty(value = "设备类型名称")
    private String sensorClassifyName;

    @ApiModelProperty(value = "设备状态1 在线  0离线")
    @Excel(name = "设备状态1 在线  0离线")
    private Integer state;

    @ApiModelProperty(value = "黄色报警值")
    @Excel(name = "黄色报警")
    private BigDecimal yellowAlarm;

    @ApiModelProperty(value = "红色报警值")
    @Excel(name = "红色报警")
    private BigDecimal redAlarm;

    @ApiModelProperty(value = "橙色报警值")
    @Excel(name = "橙色报警")
    private BigDecimal orangeAlarm;

    @ApiModelProperty(value = "量程最小值")
    @Excel(name = "量程最小值")
    private BigDecimal rangeMin;


    @ApiModelProperty(value = "传感器协议id")
    private Long sensorProtocolId;

    @ApiModelProperty(value = "协议名称")
    private String sensorProtocolName;

    @ApiModelProperty(value = "量程最大值")
    @Excel(name = "量程最大值")
    private BigDecimal rangeMax;

    @ApiModelProperty(value = "检测类型id")
    @Excel(name = "检测类型id")
    private Long detectionTypeId;

    @Excel(name = "检测类型")
    @ApiModelProperty(value = "检测类型")
    private String detectionType;

    private Long modifyBy;

    private Long createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

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
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setAddress(Long address) 
    {
        this.address = address;
    }

    public Long getAddress() 
    {
        return address;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setX(BigDecimal x) 
    {
        this.x = x;
    }

    public BigDecimal getX() 
    {
        return x;
    }
    public void setZ(BigDecimal z) 
    {
        this.z = z;
    }

    public BigDecimal getZ() 
    {
        return z;
    }
    public void setY(BigDecimal y) 
    {
        this.y = y;
    }

    public BigDecimal getY() 
    {
        return y;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setLineLength(BigDecimal lineLength) 
    {
        this.lineLength = lineLength;
    }

    public BigDecimal getLineLength() 
    {
        return lineLength;
    }
    public void setSensorClassifyId(Long sensorClassifyId) 
    {
        this.sensorClassifyId = sensorClassifyId;
    }

    public Long getSensorClassifyId() 
    {
        return sensorClassifyId;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }
    public void setYellowAlarm(BigDecimal yellowAlarm) 
    {
        this.yellowAlarm = yellowAlarm;
    }

    public BigDecimal getYellowAlarm() 
    {
        return yellowAlarm;
    }
    public void setRedAlarm(BigDecimal redAlarm) 
    {
        this.redAlarm = redAlarm;
    }

    public BigDecimal getRedAlarm() 
    {
        return redAlarm;
    }
    public void setOrangeAlarm(BigDecimal orangeAlarm) 
    {
        this.orangeAlarm = orangeAlarm;
    }

    public BigDecimal getOrangeAlarm() 
    {
        return orangeAlarm;
    }
    public void setRangeMin(BigDecimal rangeMin) 
    {
        this.rangeMin = rangeMin;
    }

    public BigDecimal getRangeMin() 
    {
        return rangeMin;
    }
    public void setRangeMax(BigDecimal rangeMax) 
    {
        this.rangeMax = rangeMax;
    }

    public BigDecimal getRangeMax() 
    {
        return rangeMax;
    }
    public void setDetectionTypeId(Long detectionTypeId) 
    {
        this.detectionTypeId = detectionTypeId;
    }

    public Long getDetectionTypeId() 
    {
        return detectionTypeId;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupIp() {
        return groupIp;
    }

    public void setGroupIp(String groupIp) {
        this.groupIp = groupIp;
    }

    public String getSensorClassifyName() {
        return sensorClassifyName;
    }

    public void setSensorClassifyName(String sensorClassifyName) {
        this.sensorClassifyName = sensorClassifyName;
    }

    public String getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(String detectionType) {
        this.detectionType = detectionType;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
