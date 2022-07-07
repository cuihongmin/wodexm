package com.wanqiao.mogao.project.publishfeedback.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;
import org.springframework.data.annotation.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 报警： 包括电话报警、短信邮件等对象 bus_alarm
 * 
 * @author cjj
 * @date 2021-05-06
 */
@ApiModel(value = "报警记录")
public class BusAlarm implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    @ApiModelProperty(value = "报警方式Id")
    private Long alarmWayId;

    @ApiModelProperty(value = "安全员id")
    private Long safetyId;

    @Transient
    @ApiModelProperty(value = "安全人员编号", notes = "用于查询")
    @Excel(name = "安全人员编号")
    private String safetyCode;

    @Transient
    @ApiModelProperty(value = "安全人员名称", notes = "用于查询")
    @Excel(name = "安全人员名称")
    private String safetyName;

    @ApiModelProperty(value = "部门id", notes = "查询使用")
    @Transient
    private Long deptId;

    @Transient
    @ApiModelProperty(value = "部门名称")
    @Excel(name = "部门")
    private String deptName;

    @Transient
    @ApiModelProperty(value = "职务名称")
    @Excel(name = "职务")
    private String positionName;

    @Transient
    @ApiModelProperty(value = "手机号码")
    @Excel(name = "手机号码")
    private String mobilePhone;

    @Transient
    @ApiModelProperty(value = "邮箱")
    @Excel(name = "手机号码")
    private String email;

    @ApiModelProperty(value = "报警级别id ")
    @Transient
    private Integer warnLevelId;

    @ApiModelProperty(value = "报警级别")
    @Excel(name = "报警级别")
    private String warnLevel;

    /** 报警内容 */
    @Excel(name = "报警内容")
    private String content;

    @ApiModelProperty(value = "发送状态 1发送,  0发送失败")
    @Excel(name = "发送状态 1发送,  0发送失败")
    private Integer sendState;

    @Transient
    @ApiModelProperty(value = "记录加载人")
    private String recordLoadUser;

    @Transient
    @ApiModelProperty(value = "保存时选择的安全人员")
    private List<Long> safetyPeopleIds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "加载人id")
    private Long createBy;

    @ApiModelProperty(value = "发送类型", notes = "手动发送为1")
    private int sendType = 1;

    /** 开始时间 */
    @JsonIgnore
    private String beginTime;

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

    /** 结束时间 */
    @JsonIgnore
    private String endTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAlarmWayId(Long alarmWayId) 
    {
        this.alarmWayId = alarmWayId;
    }

    public Long getAlarmWayId() 
    {
        return alarmWayId;
    }
    public void setSafetyId(Long safetyId) 
    {
        this.safetyId = safetyId;
    }
    public void setWarnLevelId(Integer warnLevelId)
    {
        this.warnLevelId = warnLevelId;
    }

    public Integer getWarnLevelId() 
    {
        return warnLevelId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setSendState(Integer sendState) 
    {
        this.sendState = sendState;
    }

    public Integer getSendState() 
    {
        return sendState;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public List<Long> getSafetyPeopleIds() {
        return safetyPeopleIds;
    }

    public void setSafetyPeopleIds(List<Long> safetyPeopleIds) {
        this.safetyPeopleIds = safetyPeopleIds;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSafetyId() {
        return safetyId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getSafetyCode() {
        return safetyCode;
    }

    public void setSafetyCode(String safetyCode) {
        this.safetyCode = safetyCode;
    }

    public String getSafetyName() {
        return safetyName;
    }

    public void setSafetyName(String safetyName) {
        this.safetyName = safetyName;
    }

    public String getRecordLoadUser() {
        return recordLoadUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public void setRecordLoadUser(String recordLoadUser) {
        this.recordLoadUser = recordLoadUser;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .toString();
    }
}
