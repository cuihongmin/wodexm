package com.wanqiao.mogao.project.sensor.datacollect.message;

import com.wanqiao.mogao.common.utils.WKCalcuUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.List;

/**
 * 编解码
 */
public class OsmometerMessageCode extends MessageToMessageCodec<ByteBuf, OsmometerBaseProtocol> {

    //相应报文长度
    private final int lengthFrame;

    public OsmometerMessageCode(int lengthFrame) {
        this.lengthFrame = lengthFrame;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, OsmometerBaseProtocol osm, List<Object> list) throws Exception {
        System.out.println("执行了编码方法 encode");
        ByteBuf out = ctx.alloc().buffer();
        out.writeBytes(osm.getMessageBody());
        String crcCode = WKCalcuUtil.getCRC(osm.getMessageBody());
        out.writeBytes(new byte[] {(byte) Integer.valueOf(crcCode.substring(0,2), 16).intValue(),
        (byte) Integer.valueOf(crcCode.substring(2,4), 16).intValue()});
        System.out.println("请求报文 = " + ByteBufUtil.hexDump(out));
        list.add(out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        System.out.println("执行解码方法  decode");
        ByteBuf b = (ByteBuf) this.decode(ctx, in);
        if(b != null) {
            int length = b.readableBytes();
            String hexStr = ByteBufUtil.hexDump(b);
            System.out.println("解码输出结果 = " + hexStr);
            OsmometerResponse response = new OsmometerResponse();
            if(length == 5) {
                response.setRequestType(1);
                //请求报文错误
                String errorCode = hexStr.substring(4, 6);
                if("01".equals(errorCode)) {
                    response.setMessageState(1);
                } else if("02".equals(errorCode)) {
                    response.setMessageState(2);
                } else if("03".equals(errorCode)) {
                    response.setMessageState(3);
                }
            }

            if(length == this.lengthFrame){
                response.setRequestType(2);
                String body = hexStr.substring(6, 26);
                String depth = body.substring(0, 8);
                String temperatureCode = body.substring(8, 12);
                String waterPressureCode = body.substring(12, body.length());

                response.setDepth(depth);
                response.setTemperature(temperatureCode);
                response.setWaterPressure(waterPressureCode);
            }
            list.add(response);
        }

    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int length = in.readableBytes();
        if(length < this.lengthFrame) {
            return length < 5 ? null : in.readRetainedSlice(length);
        } else {
            return in.readRetainedSlice(this.lengthFrame);
        }
    }
}