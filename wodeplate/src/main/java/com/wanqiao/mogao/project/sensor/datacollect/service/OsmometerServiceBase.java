package com.wanqiao.mogao.project.sensor.datacollect.service;

import com.wanqiao.mogao.project.sensor.datacollect.message.OsmometerResponse;

public  abstract class OsmometerServiceBase {
    /**
     * 保存测斜仪数据
     * @param respone
     */
    public abstract void saveData(OsmometerResponse respone);
}
