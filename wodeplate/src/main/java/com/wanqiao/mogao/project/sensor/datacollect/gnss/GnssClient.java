package com.wanqiao.mogao.project.sensor.datacollect.gnss;

import com.wanqiao.mogao.project.sensor.datacollect.service.GnssServiceBase;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.net.InetSocketAddress;

public class GnssClient {

    private String address;
    private Integer port;
    private GnssClientHandle haddle;

    public GnssClient(String address, Integer port, GnssClientHandle haddle) {
        this.address = address;
        this.port = port;
        this.haddle = haddle;
    }

    private void start()  {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(this.address, port))
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            ch.pipeline().addLast(haddle);
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        /*GnssClient gnss = new GnssClient("192.168.100.247", 7777);
        gnss.start();*/
    }
}
