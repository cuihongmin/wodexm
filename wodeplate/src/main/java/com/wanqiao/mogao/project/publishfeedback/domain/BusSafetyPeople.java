package com.wanqiao.mogao.project.publishfeedback.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;
import org.springframework.data.annotation.Transient;

/**
 * 安全人员对象 bus_safety_people
 * 
 * @author geshanghua
 * @date 2021-05-10
 */
public class BusSafetyPeople extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;


    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 关联账号id */
    @Excel(name = "关联账号id")
    private Long relevanceUserId;

    /** $column.columnComment */
    @Excel(name = "关联账号id")
    private String code;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "部门名称")
    @Transient
    private String deptName;

    /** 职务id */
    @Excel(name = "职务id")
    private Long positionId;

    @Transient
    @Excel(name = "职位名称")
    private String positionName;

    /** 手机 */
    @Excel(name = "手机")
    private String mobilePhone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /** $column.columnComment */
    @Excel(name = "邮箱")
    private Long modifyBy;

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

    public String getName()
    {
        return name;
    }
    public void setRelevanceUserId(Long relevanceUserId) 
    {
        this.relevanceUserId = relevanceUserId;
    }

    public Long getRelevanceUserId() 
    {
        return relevanceUserId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setPositionId(Long positionId) 
    {
        this.positionId = positionId;
    }

    public Long getPositionId() 
    {
        return positionId;
    }
    public void setMobilePhone(String mobilePhone) 
    {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() 
    {
        return mobilePhone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("relevanceUserId", getRelevanceUserId())
            .append("code", getCode())
            .append("deptId", getDeptId())
            .append("positionId", getPositionId())
            .append("mobilePhone", getMobilePhone())
            .append("email", getEmail())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .append("createBy", getCreateBy())
            .append("modifyBy", getModifyBy())
            .toString();
    }
}
