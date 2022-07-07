package com.wanqiao.mogao.project.sensor.datacollect.gnss;

import com.wanqiao.mogao.project.sensor.datacollect.message.GnssResponse;
import com.wanqiao.mogao.project.sensor.datacollect.service.GnssServiceBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component
@Slf4j
@ChannelHandler.Sharable
public class GnssClientHandle extends SimpleChannelInboundHandler<ByteBuf> {

    @Autowired
    private GnssServiceBase base;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("gnss客户端和服务端建立了连接");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("gnss服务端关闭连接");
        ctx.channel().close();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("gnss 客户端抛出异常  e = {}", cause);
        ctx.channel().close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf b) throws Exception {
        String end = b.toString(CharsetUtil.UTF_8);
        String[] contents = end.split("\r\n");
        if(contents.length == 3) {
            GnssResponse response = new GnssResponse();
            String body = contents[0];
            if(body.contains("$MEMS") && body.contains("*")) {
                String[] content = body.split(",");
                response.setSpeedx(new BigDecimal(content[2]));
                response.setSpeedy(new BigDecimal(content[3]));
                response.setSpeedz(new BigDecimal(content[4]));
                response.setRoll(new BigDecimal(content[5]));
                response.setPitch(new BigDecimal(content[6]));
                response.setYaw(new BigDecimal(content[7]));
                this.base.saveData(response);
            }
        }
    }
}
