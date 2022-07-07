package com.wanqiao.mogao.project.sensor.datacollect.osmometer;

import com.wanqiao.mogao.project.sensor.datacollect.message.OsmometerBaseProtocol;
import com.wanqiao.mogao.project.sensor.datacollect.message.OsmometerRequest;
import com.wanqiao.mogao.project.sensor.datacollect.message.OsmometerResponse;
import com.wanqiao.mogao.project.sensor.datacollect.service.OsmometerServiceBase;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ChannelHandler.Sharable
public class ModbusClientHandler extends SimpleChannelInboundHandler<OsmometerBaseProtocol> {

    /**
     * 休眠时间
     */
    private int sleepTime = 1000 * 6000;

    @Autowired
    private OsmometerServiceBase base;



    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * 在到服务器的连接已经建立之后将被调用
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
        log.info("和服务端建立连接  channelActive");
        /**
         * channelActive只能异步执行不然次方法执行不玩消息发送不出去
         */
        new Thread(() -> {
            while (true) {
                OsmometerRequest request = new OsmometerRequest();
                request.setAddress(0x2c);
                request.setFunctionCode(0x03);
                request.setRegisterHAddress(0x00);
                request.setRegisterLAddress(0x01);
                request.setRegisterHnum(0x00);
                request.setRegisterLnum(0x05);
                ctx.writeAndFlush(request);
                try {
                    Thread.sleep(this.sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("服务端关闭连接");
        ctx.channel().close();
    }

    /**
     * 在处理过程中引发异常时被调用
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("调用客户端方法  exceptionCaught");
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, OsmometerBaseProtocol osm) throws Exception {
        OsmometerResponse respone = (OsmometerResponse) osm;
        log.info("采集的数据  = {}", respone.toString());
        this.base.saveData(respone);
    }
}
