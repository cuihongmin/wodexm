package com.wanqiao.mogao.project.region.domain;

import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

/**
 * 行政区划对象 sys_admin_division
 * 
 * @author xubobo
 * @date 2020-07-15
 */
public class SysAdminDivision extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String regionName;

    /** 级别 */
    @Excel(name = "级别")
    private String regionLevel;

    /** 区划编码 */
    @Excel(name = "区划编码")
    private String regionCode;

    /** 父级编码 */
    @Excel(name = "父级编码")
    private String parentCode;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRegionName(String regionName) 
    {
        this.regionName = regionName;
    }

    public String getRegionName() 
    {
        return regionName;
    }
    public void setRegionLevel(String regionLevel) 
    {
        this.regionLevel = regionLevel;
    }

    public String getRegionLevel() 
    {
        return regionLevel;
    }
    public void setRegionCode(String regionCode) 
    {
        this.regionCode = regionCode;
    }

    public String getRegionCode() 
    {
        return regionCode;
    }
    public void setParentCode(String parentCode) 
    {
        this.parentCode = parentCode;
    }

    public String getParentCode() 
    {
        return parentCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("regionName", getRegionName())
            .append("regionLevel", getRegionLevel())
            .append("regionCode", getRegionCode())
            .append("parentCode", getParentCode())
            .toString();
    }
}
