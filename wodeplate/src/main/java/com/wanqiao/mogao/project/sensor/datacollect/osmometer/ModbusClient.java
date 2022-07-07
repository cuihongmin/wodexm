package com.wanqiao.mogao.project.sensor.datacollect.osmometer;

import com.wanqiao.mogao.project.sensor.datacollect.message.OsmometerMessageCode;
import com.wanqiao.mogao.project.sensor.datacollect.service.OsmometerServiceBase;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class ModbusClient {

    private final String host;
    private final int port;
    private ModbusClientHandler handler;
    private int sleepTime;

    public ModbusClient(String host, int port,  ModbusClientHandler handle, int sleepTime) {
        this.host = host;
        this.port = port;
        this.handler = handle;
        this.sleepTime = sleepTime;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            ch.pipeline().addLast(new OsmometerMessageCode(15));
                            ch.pipeline().addLast(handler);
                        }
                    });
            ChannelFuture f = b.connect().sync();

            //监听端口是否连通
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture c) throws Exception {
                    if(c.isSuccess()) {
                        System.out.println("已经建立连接");
                    }
                }
            });
            //这个方式是阻塞的他监听关闭通道事件，当通道关闭时调用
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        /*String host = "192.168.100.229";
        int port = 5001;
        int byteLength = 8;
        Bootstrap b = new ModbusClient(host, port,15).start();*/
    }
}