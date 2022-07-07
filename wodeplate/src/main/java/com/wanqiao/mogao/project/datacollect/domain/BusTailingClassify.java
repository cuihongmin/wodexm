package com.wanqiao.mogao.project.datacollect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;

/**
 * 尾矿库分类对象 bus_tailing_classify
 * 
 * @author zhangguangbin
 * @date 2021-05-08
 */
public class BusTailingClassify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 尾矿库分类名称 */
    @Excel(name = "尾矿库分类名称")
    private String name;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
