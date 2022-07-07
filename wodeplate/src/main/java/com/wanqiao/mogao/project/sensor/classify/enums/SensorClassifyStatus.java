package com.wanqiao.mogao.project.sensor.classify.enums;

/**
 * @author: 李向平
 * @version: 创建时间2021/5/8
 * <p>
 * 类描述: 传感设备状态枚举类
 */
public enum SensorClassifyStatus
{
    DISABLE(0, "禁用"),
    OK(1, "启用");

    private final Integer code;
    private final String info;

    SensorClassifyStatus(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
