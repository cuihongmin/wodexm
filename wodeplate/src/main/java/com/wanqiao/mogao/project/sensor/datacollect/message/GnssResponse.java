package com.wanqiao.mogao.project.sensor.datacollect.message;

import java.math.BigDecimal;

public class GnssResponse {
    //加速度x
    private BigDecimal speedx;
    //加速度y
    private BigDecimal speedy;
    //加速度z
    private BigDecimal speedz;
    //滚转角
    private BigDecimal roll;
    //俯仰角
    private BigDecimal pitch;
    //偏航角
    private BigDecimal yaw;

    public BigDecimal getSpeedx() {
        return speedx;
    }

    public void setSpeedx(BigDecimal speedx) {
        this.speedx = speedx;
    }

    public BigDecimal getSpeedy() {
        return speedy;
    }

    public void setSpeedy(BigDecimal speedy) {
        this.speedy = speedy;
    }

    public BigDecimal getSpeedz() {
        return speedz;
    }

    public void setSpeedz(BigDecimal speedz) {
        this.speedz = speedz;
    }

    public BigDecimal getRoll() {
        return roll;
    }

    public void setRoll(BigDecimal roll) {
        this.roll = roll;
    }

    public BigDecimal getPitch() {
        return pitch;
    }

    public void setPitch(BigDecimal pitch) {
        this.pitch = pitch;
    }

    public BigDecimal getYaw() {
        return yaw;
    }

    public void setYaw(BigDecimal yaw) {
        this.yaw = yaw;
    }

    @Override
    public String toString() {
        return "GnssResponse{" +
                "speedx=" + speedx +
                ", speedy=" + speedy +
                ", speedz=" + speedz +
                ", roll=" + roll +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                '}';
    }
}
