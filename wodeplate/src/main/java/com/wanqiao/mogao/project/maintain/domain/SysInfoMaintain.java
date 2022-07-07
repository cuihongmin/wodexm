package com.wanqiao.mogao.project.maintain.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

/**
 * 系统信息维护对象 sys_info_maintain
 * 
 * @author zsh
 * @date 2021-04-22
 */
@ApiModel("系统信息维护实体")
public class SysInfoMaintain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统ID */
    @ApiModelProperty("系统ID")
    private Long sysId;

    /** 系统栏目 */
    @ApiModelProperty("系统栏目")
    @Excel(name = "系统栏目")
    private String sysColumn;

    /** 标题 */
    @ApiModelProperty("标题")
    private String sysTitle;

    /** 内容 */
    @ApiModelProperty("内容")
    private String sysContent;

    /** 状态（0正常 1关闭） */
    @ApiModelProperty("状态（0正常 1关闭）")
    private String status;

    /** 创建者姓名 */
    @ApiModelProperty("创建者姓名")
    private String createName;

    /** 修改者姓名 */
    @ApiModelProperty("修改者姓名")
    private String updateName;

    public void setSysId(Long sysId) 
    {
        this.sysId = sysId;
    }

    public Long getSysId() 
    {
        return sysId;
    }
    public void setSysColumn(String sysColumn) 
    {
        this.sysColumn = sysColumn;
    }

    public String getSysColumn() 
    {
        return sysColumn;
    }
    public void setSysTitle(String sysTitle) 
    {
        this.sysTitle = sysTitle;
    }

    public String getSysTitle() 
    {
        return sysTitle;
    }
    public void setSysContent(String sysContent) 
    {
        this.sysContent = sysContent;
    }

    public String getSysContent() 
    {
        return sysContent;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCreateName(String createName) 
    {
        this.createName = createName;
    }

    public String getCreateName() 
    {
        return createName;
    }
    public void setUpdateName(String updateName) 
    {
        this.updateName = updateName;
    }

    public String getUpdateName() 
    {
        return updateName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sysId", getSysId())
            .append("sysColumn", getSysColumn())
            .append("sysTitle", getSysTitle())
            .append("sysContent", getSysContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createName", getCreateName())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateName", getUpdateName())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
