package com.wanqiao.mogao.project.sensor.classify.enums;

/**
 * @author: 李向平
 * @version: 创建时间2021/5/8
 * <p>
 * 类描述: 传感设备采集时间单位枚举类
 */
public enum CollectTimeUnit
{
    SECOND("s", "秒"),
    MINUTE("m", "分钟"),
    HOUR("h", "小时");

    private final String unit;
    private final String desc;

    CollectTimeUnit(String unit, String desc)
    {
        this.unit = unit;
        this.desc = desc;
    }

    public String getUnit()
    {
        return unit;
    }

    public String getDesc()
    {
        return desc;
    }
}
