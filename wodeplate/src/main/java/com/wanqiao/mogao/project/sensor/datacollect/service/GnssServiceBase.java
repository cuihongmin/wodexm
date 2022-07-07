package com.wanqiao.mogao.project.sensor.datacollect.service;

import com.wanqiao.mogao.project.sensor.datacollect.message.GnssResponse;

public abstract class GnssServiceBase {


    /**
     * 保存gnss数据
     */
    public abstract void saveData(GnssResponse response);
}
